package com.k41.repository;

import com.k41.entity.CoSo;
import com.k41.entity.NguoiHienMau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiHienMauRepository extends JpaRepository<NguoiHienMau, Long> {
    Page<NguoiHienMau> findByCoSoId(Long id, Pageable paging);
}
