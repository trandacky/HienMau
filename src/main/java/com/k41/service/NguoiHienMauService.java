package com.k41.service;

import com.k41.entity.CoSo;
import com.k41.entity.NguoiHienMau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NguoiHienMauService {
    Page<NguoiHienMau> findPageByCoSoId(Pageable paging, Long id);
}
