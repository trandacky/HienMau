package com.k41.repository;

import com.k41.entity.CoSo;
import com.k41.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

// class chịu trách nhiệm tạo câu query vào database
public interface CoSoRepository extends JpaRepository<CoSo, Long> {
}
