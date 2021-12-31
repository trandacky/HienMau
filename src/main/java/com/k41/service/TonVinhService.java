package com.k41.service;

import com.k41.entity.TonVinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TonVinhService {
    Page<TonVinh> findPage(int year, Pageable paging);
}
