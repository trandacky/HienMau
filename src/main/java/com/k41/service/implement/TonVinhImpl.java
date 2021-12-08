package com.k41.service.implement;

import com.k41.repository.CoSoRepository;
import com.k41.repository.TonVinhRepository;
import com.k41.service.NguoiDungService;
import com.k41.service.TonVinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TonVinhImpl implements TonVinhService {
    @Autowired
    private TonVinhRepository tonVinhRepository;

}
