package com.k41.repository;

import com.k41.entity.NguoiDung;
import com.k41.entity.QuyenNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuyenNguoiDungRepository extends JpaRepository<QuyenNguoiDung, Long> {
}
