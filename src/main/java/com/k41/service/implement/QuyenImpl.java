package com.k41.service.implement;

import com.k41.entity.Quyen;
import com.k41.repository.QuyenRepository;
import com.k41.service.QuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuyenImpl implements QuyenService {
    @Autowired
    private QuyenRepository quyenRepository;

    @Override
    public Quyen findByRole(String role) {
        return quyenRepository.findByRole(role);
    }
}
