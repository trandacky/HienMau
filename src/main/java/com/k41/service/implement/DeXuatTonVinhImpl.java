package com.k41.service.implement;

import com.k41.entity.DeXuatTonVinh;
import com.k41.repository.CoSoRepository;
import com.k41.repository.DeXuatTonVinhRepository;
import com.k41.service.DeXuatTonVinhService;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeXuatTonVinhImpl implements DeXuatTonVinhService {
    @Autowired
    private DeXuatTonVinhRepository deXuatTonVinhRepository;

    @Override
    public List<DeXuatTonVinh> findAll() {
        return deXuatTonVinhRepository.findAll(Sort.by(Sort.Direction.DESC, "mucTonVinh"));
    }

    @Override
    public Page<DeXuatTonVinh> findPage(Pageable paging) {
        return deXuatTonVinhRepository.findAll(paging);
    }
}
