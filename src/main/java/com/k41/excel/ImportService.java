package com.k41.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ImportService {
    @Transactional
    public List<ImportDTO> importExcelFile(MultipartFile file) throws Exception {
        List<ImportDTO> importDTOS;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            Import<ImportDTO> excelImporter = new Import<>(ImportDTO.class);
            excelImporter.importFile(file.getInputStream());
            importDTOS = excelImporter.getListData();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return importDTOS;
    }

}
