package com.k41.service.implement;

import com.k41.controller.admin.LichSuVinhDanh;
import com.k41.entity.DeXuatTonVinh;
import com.k41.entity.NguoiHienMau;
import com.k41.entity.TonVinh;
import com.k41.repository.CoSoRepository;
import com.k41.repository.DeXuatTonVinhRepository;
import com.k41.repository.NguoiHienMauRepository;
import com.k41.repository.TonVinhRepository;
import com.k41.service.DeXuatTonVinhService;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeXuatTonVinhImpl implements DeXuatTonVinhService {
    @Autowired
    private DeXuatTonVinhRepository deXuatTonVinhRepository;

    @Autowired
    private TonVinhRepository tonVinhRepository;
    @Autowired
    private NguoiHienMauRepository nguoiHienMauRepository;

    @Override
    public List<DeXuatTonVinh> findAll() {
        return deXuatTonVinhRepository.findAll(Sort.by(Sort.Direction.DESC, "mucTonVinh"));
    }

    @Override
    public Page<DeXuatTonVinh> findPage(Pageable paging) {
        return deXuatTonVinhRepository.findAll(paging);
    }

    @Override
    public void deXuat() {
        List<DeXuatTonVinh> deXuatTonVinhs = deXuatTonVinhRepository.findAll();
        List<TonVinh> tonVinhs = new ArrayList<>();
        List<NguoiHienMau> nguoiHienMaus = new ArrayList<>();
        TonVinh tonVinh;
        NguoiHienMau nguoiHienMau;
        for (DeXuatTonVinh deXuatTonVinh : deXuatTonVinhs) {
            tonVinh = new TonVinh();
            tonVinh.setMucTonVinh(deXuatTonVinh.getMucTonVinh());
            tonVinh.setNgayTonVinh(deXuatTonVinh.getNgayTonVinh());
            tonVinh.setNguoiHienMau(deXuatTonVinh.getNguoiHienMau());
            tonVinhs.add(tonVinh);
            nguoiHienMau = tonVinh.getNguoiHienMau();
            nguoiHienMau.setMucTonVinh(tonVinh.getMucTonVinh());
            nguoiHienMaus.add(nguoiHienMau);
        }
        tonVinhRepository.saveAll(tonVinhs);
        nguoiHienMauRepository.saveAll(nguoiHienMaus);
    }

    @Override
    public DeXuatTonVinh findById(Long id) {
        return deXuatTonVinhRepository.findById(id).get();
    }

    @Override
    public DeXuatTonVinh save(DeXuatTonVinh deXuatTonVinh) {
        return deXuatTonVinhRepository.save(deXuatTonVinh);
    }
}
