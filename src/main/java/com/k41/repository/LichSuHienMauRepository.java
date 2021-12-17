package com.k41.repository;

import com.k41.entity.LichSuHienMau;
import com.k41.entity.NguoiHienMau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface LichSuHienMauRepository extends JpaRepository<LichSuHienMau, Long> {
    Optional<LichSuHienMau> findFirstByNguoiHienMauAndNgayHienMau(NguoiHienMau nguoiHienMau, Instant ngayHienMau);
}
