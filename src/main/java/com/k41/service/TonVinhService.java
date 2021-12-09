package com.k41.service;

import com.k41.entity.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TonVinhService {
    Page<NguoiDung> findPage(Pageable paging);
}
