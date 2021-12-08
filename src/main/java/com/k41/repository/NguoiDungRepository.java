package com.k41.repository;

import com.k41.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findFirstByActiveKey(String uuid);

    Optional<NguoiDung> findFirstByTenDangNhapOrEmailLike(String dangNhapOrEmail, String tenDangNhapOrEmail);
}
