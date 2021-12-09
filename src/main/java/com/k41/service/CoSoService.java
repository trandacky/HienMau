package com.k41.service;

import com.k41.entity.CoSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoSoService {
    Page<CoSo> findPage(Pageable paging);

    CoSo save(CoSo coSo);

    CoSo updateEnable(Long id);

    CoSo findById(Long id);
}
