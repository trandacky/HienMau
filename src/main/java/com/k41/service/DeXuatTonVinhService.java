package com.k41.service;

import com.k41.entity.DeXuatTonVinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeXuatTonVinhService {
    List<DeXuatTonVinh> findAll();

    Page<DeXuatTonVinh> findPage(Pageable paging);

    void deXuat();

    DeXuatTonVinh findById(Long id);

    DeXuatTonVinh save(DeXuatTonVinh deXuatTonVinh);
}
