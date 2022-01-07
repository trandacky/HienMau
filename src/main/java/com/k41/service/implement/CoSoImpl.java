package com.k41.service.implement;

import com.k41.entity.CoSo;
import com.k41.repository.CoSoRepository;
import com.k41.service.CoSoService;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// dịch vụ để sử dụng dành cho cơ sở
@Service
public class CoSoImpl implements CoSoService {
    @Autowired
    private CoSoRepository coSoRepository;

    @Override
    public Page<CoSo> findPage(Pageable paging) {
        return coSoRepository.findAll(paging);
    }

    @Override
    public CoSo save(CoSo coSo) {
        return coSoRepository.save(coSo);
    }

    @Override
    public CoSo updateEnable(Long id) {
        CoSo coSo =coSoRepository.findById(id).get();
        coSo.setTinhTrangHoatDong(!coSo.isTinhTrangHoatDong());
        return coSoRepository.save(coSo);
    }

    @Override
    public CoSo findById(Long id) {
        return coSoRepository.findById(id).get();
    }
}
