package com.k41.repository;

import com.k41.entity.CoSo;
import com.k41.entity.NguoiHienMau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// class chịu trách nhiệm tạo câu query vào database
public interface NguoiHienMauRepository extends JpaRepository<NguoiHienMau, Long> {
    Page<NguoiHienMau> findByCoSoId(Long id, Pageable paging);

    @Query("SELECT nhm FROM NguoiHienMau nhm WHERE nhm.cmndOrCccd = :cmndOrCccd ")
    Optional<NguoiHienMau> findByCmndOrCccd(String cmndOrCccd);
    @Query("SELECT nhm FROM NguoiHienMau nhm WHERE nhm.mucTonVinh < nhm.soLanHienMau ORDER BY nhm.soLanHienMau DESC")
    List<NguoiHienMau> findDeXuatTonVinh();
}
