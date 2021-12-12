package com.k41.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Import<T extends ImportDTO> {

    private Map<Integer, Field> fieldsMap;

    private DataFormatter dataFormatter = new DataFormatter();

    private Class<T> classImport;

    private List<ImportDTO> listData;

    public Import(Class<T> classImport) {
        this.classImport = classImport;
        listData = new ArrayList<>();
        fieldsMap = new HashMap<>();
        setFieldsMap();
    }

    public void importFile(InputStream inputStream) throws Exception {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        readData(sheet);
        workbook.close();
    }

    private void readData(Sheet sheet) throws Exception {
        List<ImportDTO> importDTOS;
        ImportDTO dto;
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                dto = (ImportDTO) instantiatedObjClassImport();
                readRowData(dto, row);
                listData.add(dto);
            }
        }
    }

    private <T> void readRowData(T dto, Row row) throws Exception {
        Class<?> dtoClass = dto.getClass();
        int lastColumn = row.getLastCellNum();
        for (int beginLocation = 0; beginLocation < lastColumn; beginLocation++) {
            Cell cell = row.getCell(beginLocation, MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Field field = fieldsMap.get(cell.getColumnIndex());
            Method method = getMethod(field, dtoClass);
            switch (cell.getCellType()) {
                case NUMERIC: {
                    if (field.getType().isAssignableFrom(String.class)) {
                        String strValue = dataFormatter.formatCellValue(cell);
                        method.invoke(dto, strValue);

                    } else if (field.getType().isAssignableFrom(Date.class)) {
                        method.invoke(dto, cell.getDateCellValue());

                    } else if (field.getType().isAssignableFrom(Boolean.class)) {
                        throw new TypeNotPresentException("Type invalid", new Exception());

                    } else {
                        readNumberValue(field, method, dto, cell.getNumericCellValue());
                    }
                }
                break;
                case STRING:
                    if (field.getType().isAssignableFrom(String.class)) {
                        method.invoke(dto, cell.getStringCellValue());
                    }
                    break;
                case BOOLEAN:
                    if (field.getType().isAssignableFrom(Boolean.class)) {
                        method.invoke(dto, cell.getBooleanCellValue());

                    } else {
                        throw new TypeNotPresentException("Type Invalid", new Exception());
                    }
                    break;
                case BLANK:
                    method.invoke(dto, "");
                default:
                    throw new TypeNotPresentException("Type Not found", new Exception());
            }

        }
    }

    private void setFieldsMap() {
        Field[] arrayFields = classImport.getDeclaredFields();
        for (int i = 0; i < arrayFields.length; i++) {
            fieldsMap.put(i, arrayFields[i]);
        }
    }

    private <T> void readNumberValue(Field field, Method method, T item, Double value) throws Exception {
        switch (field.getType().getSimpleName()) {
            case "Double":
                method.invoke(item, value);
                break;
            case "Float":
                method.invoke(item, value.floatValue());
                break;
            case "Long":
                method.invoke(item, value.longValue());
                break;
            case "Integer":
                method.invoke(item, value.intValue());
                break;
            case "Short":
                method.invoke(item, value.shortValue());
                break;
            case "Byte":
                method.invoke(item, value.byteValue());
                break;
            default:
                break;
        }
    }

    private <T> Method getMethod(Field field, Class<T> itemClass) throws Exception {
        String fieldName = field.getName().replaceFirst(field.getName().substring(0, 1),
                field.getName().substring(0, 1).toUpperCase());
        return itemClass.getMethod("set" + fieldName, Class.forName(field.getType().getName()));
    }

    private Object instantiatedObjClassImport() throws Exception {
        Constructor<?> constructor = classImport.getConstructors()[0];
        return constructor.newInstance();
    }

    public List<ImportDTO> getListData() {
        return listData;
    }
}