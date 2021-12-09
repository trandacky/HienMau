package com.k41.service;

import com.k41.entity.Quyen;
import com.k41.entity.QuyenNguoiDung;

public interface QuyenService {
    Quyen findByRole(String role);
}
