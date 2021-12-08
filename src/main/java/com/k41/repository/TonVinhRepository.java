package com.k41.repository;

import com.k41.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TonVinhRepository extends JpaRepository<NguoiDung, Long> {
}
