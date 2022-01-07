package com.k41.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// class xuất file excel
@Slf4j
public class Export<T extends ExportDTO> {

    private int[] columnWidths;
    private int currentRow;
    private Map<Integer, Field> fieldsMap;
    private Workbook workbook = new XSSFWorkbook();
    private Sheet sheet;
    private String sheetName;
    private List<String> listHeader;
    private List<T> listData;

    public Export(String sheetName, List<String> listHeaders) {
        this.sheetName = sheetName;
        this.listHeader = listHeaders;
        initUtil();
    }

    public void generateTemplate() {
        this.sheet = workbook.createSheet(sheetName);
        int headerRowStart = currentRow++;
        writeHeader();
        fixCellWidth(sheet);
        sheet.setAutoFilter(new CellRangeAddress(headerRowStart, headerRowStart, 0, listHeader.size() - 1));
    }

    public void fillData(List<T> listData) throws Exception {
        try {
            this.listData = listData;
            insertData();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public boolean exportFile(HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            try {
                if (!ObjectUtils.isEmpty(outputStream))
                    outputStream.close();
                if (!ObjectUtils.isEmpty(workbook))
                    workbook.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return true;
    }

    private void fixCellWidth(Sheet sheet) {
        for (int i = 0; i < columnWidths.length; i++) {
            int width = (int) (columnWidths[i] < 10 ? columnWidths[i] * 2 * 256 : columnWidths[i] * 1.6 * 256);
            sheet.setColumnWidth(i, width);
        }
    }

    private void writeHeader() {
        Row headerRow = sheet.createRow(currentRow);
        for (int i = 0; i < listHeader.size(); i++) {
            String header = listHeader.get(i);
            createCell(header, i, headerRow);
            columnWidths[i] = header.length();
        }
        currentRow++;
    }

    private void initUtil() {
        columnWidths = new int[listHeader.size()];
        currentRow = 0;
        fieldsMap = new HashMap<>();
    }

    private void insertData() throws Exception {
        if (!ObjectUtils.isEmpty(listData)) {
            Class<?> classExport = listData.get(0).getClass();
            setFieldsMap(classExport);
            for (ExportDTO item : listData) {
                Row row = sheet.createRow(currentRow);
                insertRowData(item, row);
            }
        }
    }

    private void insertRowData(ExportDTO item, Row row) throws Exception {
        int columnCount = 0;
        Class<?> itemClass = item.getClass();
        for (Field field : fieldsMap.values()) {
            String fieldName = field.getName().replaceFirst(field.getName().substring(0, 1),
                    field.getName().substring(0, 1).toUpperCase());
            Method method = itemClass.getMethod("get" + fieldName);
            Object value = method.invoke(item);
            if (!ObjectUtils.isEmpty(value)) {
                createCell(value.toString(), columnCount, row);
            }
            columnCount++;
        }
        currentRow++;
    }

    private void createCell(String cellValue, int columnCount, Row row) {
        Cell cell = createCell(columnCount, row);
        cell.setCellValue(cellValue);
        updateArrayWidth(cellValue.length(), columnCount);
    }

    private void updateArrayWidth(int valueLength, int columnCount) {
        if (columnCount < columnWidths.length) {
            columnWidths[columnCount] = columnWidths[columnCount] < valueLength ? valueLength
                    : columnWidths[columnCount];
        }
    }

    private void setFieldsMap(Class<?> classExport) {
        Field[] arrayFields = classExport.getDeclaredFields();
        for (int count = 0; count < arrayFields.length; count++) {
            fieldsMap.put(count, arrayFields[count]);
        }
    }

    private Cell createCell(int columnCount, Row row) {
        Cell cell = row.createCell(columnCount);
        return cell;
    }

}
