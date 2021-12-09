package com.k41.service.implement;

import com.k41.entity.NguoiDung;
import com.k41.repository.CoSoRepository;
import com.k41.repository.TonVinhRepository;
import com.k41.service.NguoiDungService;
import com.k41.service.TonVinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TonVinhImpl implements TonVinhService {
    @Autowired
    private TonVinhRepository tonVinhRepository;

    @Override
    public Page<NguoiDung> findPage(Pageable paging) {
        return tonVinhRepository.findAll(paging);
    }
}
