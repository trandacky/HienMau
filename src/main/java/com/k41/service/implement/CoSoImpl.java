package com.k41.service.implement;

import com.k41.repository.CoSoRepository;
import com.k41.service.CoSoService;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoSoImpl implements CoSoService {
    @Autowired
    private CoSoRepository coSoRepository;
}
