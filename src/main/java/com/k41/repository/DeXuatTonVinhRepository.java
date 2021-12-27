package com.k41.repository;

import com.k41.entity.DeXuatTonVinh;
import com.k41.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeXuatTonVinhRepository extends JpaRepository<DeXuatTonVinh, Long> {
}
