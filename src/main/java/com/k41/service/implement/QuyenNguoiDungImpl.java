package com.k41.service.implement;

import com.k41.entity.QuyenNguoiDung;
import com.k41.repository.QuyenNguoiDungRepository;
import com.k41.service.QuyenNguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuyenNguoiDungImpl implements QuyenNguoiDungService {
    @Autowired
    private QuyenNguoiDungRepository quyenNguoiDungRepository;

    @Override
    public QuyenNguoiDung save(QuyenNguoiDung quyenNguoiDung) {
        return quyenNguoiDungRepository.save(quyenNguoiDung);
    }
}
