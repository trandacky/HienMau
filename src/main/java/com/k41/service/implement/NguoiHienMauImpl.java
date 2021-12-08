package com.k41.service.implement;

import com.k41.repository.CoSoRepository;
import com.k41.repository.NguoiHienMauRepository;
import com.k41.service.NguoiDungService;
import com.k41.service.NguoiHienMauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiHienMauImpl implements NguoiHienMauService {
    @Autowired
    private NguoiHienMauRepository nguoiHienMauRepository;
}
