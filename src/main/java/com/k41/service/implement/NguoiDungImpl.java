package com.k41.service.implement;

import com.k41.entity.NguoiDung;
import com.k41.repository.NguoiDungRepository;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

// dịch vụ sử dụng cho tài khoản
@Service
public class NguoiDungImpl implements NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public Page<NguoiDung> findPage(Pageable paging) {
        return nguoiDungRepository.findAll(paging);
    }

    @Override
    public NguoiDung save(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public Optional<NguoiDung> findByActiveKey(String uuid) {
        return nguoiDungRepository.findFirstByActiveKey(uuid);
    }

    @Override
    public Optional<NguoiDung> findByTenDangNhapOrEmailLike(String tenDangNhapOrEmail) {
        return nguoiDungRepository.findFirstByTenDangNhapOrEmailLike(tenDangNhapOrEmail, tenDangNhapOrEmail);
    }

    @Override
    public NguoiDung deleteActiveKey(NguoiDung nguoiDung) {
        nguoiDung.setActiveKey(null);
        return nguoiDungRepository.save(nguoiDung);
    }
}
