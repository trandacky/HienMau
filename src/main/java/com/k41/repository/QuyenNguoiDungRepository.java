package com.k41.repository;

import com.k41.entity.QuyenNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

// class chịu trách nhiệm tạo câu query vào database
public interface QuyenNguoiDungRepository extends JpaRepository<QuyenNguoiDung, Long> {
}
