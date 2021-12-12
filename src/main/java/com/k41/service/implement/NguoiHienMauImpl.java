package com.k41.service.implement;

import com.k41.entity.NguoiHienMau;
import com.k41.excel.ImportDTO;
import com.k41.repository.NguoiHienMauRepository;
import com.k41.service.NguoiHienMauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiHienMauImpl implements NguoiHienMauService {
    @Autowired
    private NguoiHienMauRepository nguoiHienMauRepository;

    @Override
    public Page<NguoiHienMau> findPageByCoSoId(Pageable paging, Long id) {
        return nguoiHienMauRepository.findByCoSoId(id, paging);
    }

    @Override
    public void saveByImportDTO(List<ImportDTO> importDTOS) {

    }

    private NguoiHienMau nguoiHienMau(ImportDTO importDTO) {
        NguoiHienMau nguoiHienMau = new NguoiHienMau();
        return nguoiHienMau;
    }
}
