package com.k41.repository;

import com.k41.entity.NguoiDung;
import com.k41.entity.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuyenRepository extends JpaRepository<Quyen, Long> {
    Quyen findByRole(String role);
}
