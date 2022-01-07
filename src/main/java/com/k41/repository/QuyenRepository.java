package com.k41.repository;

import com.k41.entity.NguoiDung;
import com.k41.entity.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;

// class chịu trách nhiệm tạo câu query vào database
public interface QuyenRepository extends JpaRepository<Quyen, Long> {
    Quyen findByRole(String role);
}
