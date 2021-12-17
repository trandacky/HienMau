package com.k41.service;

import com.k41.entity.NguoiHienMau;
import com.k41.excel.ImportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface NguoiHienMauService {
    Page<NguoiHienMau> findPageByCoSoId(Pageable paging, Long id);

    void saveByImportDTO(List<ImportDTO> importDTOS, Long id) throws ParseException;
}
