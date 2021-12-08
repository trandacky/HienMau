package com.k41.service.implement;

import com.k41.entity.NguoiDung;
import com.k41.repository.CoSoRepository;
import com.k41.repository.NguoiDungRepository;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungImpl implements NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public Page<NguoiDung> findPage(Pageable paging) {
        return nguoiDungRepository.findAll(paging);
    }
}
