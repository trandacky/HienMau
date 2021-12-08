package com.k41.service;

import com.k41.entity.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NguoiDungService {
    Page<NguoiDung> findPage(Pageable paging);
    NguoiDung save(NguoiDung nguoiDung);
    Optional<NguoiDung> findByActiveKey(String uuid);
    Optional<NguoiDung> findByTenDangNhapOrEmailLike(String tenDangNhapOrEmail);
    NguoiDung deleteActiveKey(NguoiDung nguoiDung);
}
