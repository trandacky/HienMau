package com.k41.service.implement;

import com.k41.entity.CoSo;
import com.k41.entity.LichSuHienMau;
import com.k41.entity.NguoiHienMau;
import com.k41.excel.ImportDTO;
import com.k41.repository.CoSoRepository;
import com.k41.repository.LichSuHienMauRepository;
import com.k41.repository.NguoiHienMauRepository;
import com.k41.service.NguoiHienMauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiHienMauImpl implements NguoiHienMauService {
    @Autowired
    private NguoiHienMauRepository nguoiHienMauRepository;

    @Autowired
    private CoSoRepository coSoRepository;
    @Autowired
    private LichSuHienMauRepository lichSuHienMauRepository;

    @Override
    public Page<NguoiHienMau> findPageByCoSoId(Pageable paging, Long id) {
        return nguoiHienMauRepository.findByCoSoId(id, paging);
    }

    @Override
    public void saveByImportDTO(List<ImportDTO> importDTOS, Long id) throws ParseException {
        List<NguoiHienMau> nguoiHienMaus = new ArrayList<>();
        List<LichSuHienMau> lichSuHienMaus = new ArrayList<>();
        NguoiHienMau nguoiHienMau = null;
        LichSuHienMau lichSuHienMau = null;
        for (ImportDTO importDTO : importDTOS) {
            parseImportDTO(importDTO, nguoiHienMau, lichSuHienMau, id);
            nguoiHienMaus.add(nguoiHienMau);
            lichSuHienMaus.add(lichSuHienMau);
        }
        nguoiHienMauRepository.saveAll(nguoiHienMaus);
        lichSuHienMauRepository.saveAll(lichSuHienMaus);

    }

    private void parseImportDTO(ImportDTO importDTO, NguoiHienMau nguoiHienMau, LichSuHienMau lichSuHienMau, Long idCoSo) throws ParseException {
        CoSo coSo = coSoRepository.findById(idCoSo).get();
        String cmndOrCccd = importDTO.getCmndOrCccd();
        String hoTen = importDTO.getHoTen();
        Instant ngaySinh = parseDatetime(importDTO.getNgaySinh());
        String diaChi = importDTO.getDiaChi();
        String soDienThoai = importDTO.getSoDienThoai();
        String email = importDTO.getEmail();
        boolean gioiTinh = false;
        String nhomMau = importDTO.getNhomMau();
        Instant ngayHien = parseDatetime(importDTO.getNgayHien());
        Double ml = Double.valueOf(importDTO.getMl());

        if (importDTO.getGioiTinh().equalsIgnoreCase("nam")) {
            gioiTinh = true;
        }

        Optional<NguoiHienMau> nguoiHienMauOptional = nguoiHienMauRepository.findByCmndOrCccd(cmndOrCccd);
        if (nguoiHienMauOptional.isPresent()) {
            nguoiHienMau = nguoiHienMauOptional.get();
        } else {
            nguoiHienMau = new NguoiHienMau();
            nguoiHienMau.setCmndOrCccd(cmndOrCccd);
            nguoiHienMau.setCoSo(coSo);
            nguoiHienMau.setDiaChi(diaChi);
            nguoiHienMau.setSoLanHienMau(0);
            nguoiHienMau.setHoTen(hoTen);
            nguoiHienMau.setSoDienThoai(soDienThoai);
            nguoiHienMau.setNgaySinh(ngaySinh);
            nguoiHienMau.setGioiTinh(gioiTinh);
            nguoiHienMau.setEmail(email);
        }
        Optional<LichSuHienMau> lichSuHienMau1 = lichSuHienMauRepository.findFirstByNguoiHienMauAndNgayHienMau(nguoiHienMau, ngayHien);
        if (lichSuHienMau1.isPresent()) {
            lichSuHienMau = lichSuHienMau1.get();
        } else {
            nguoiHienMau.setSoLanHienMau(nguoiHienMau.getSoLanHienMau() + 1);
            lichSuHienMau = new LichSuHienMau();
            lichSuHienMau.setNguoiHienMau(nguoiHienMau);
            lichSuHienMau.setNgayHienMau(ngayHien);
            lichSuHienMau.setMl(ml);
        }

    }


    private Instant parseDatetime(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStr = new Date();
        if (!ObjectUtils.isEmpty(date)) {
            dateStr = formatter.parse(date);
        }
        dateStr.setHours(23);
        dateStr.setMinutes(59);
        return dateStr.toInstant();
    }
}
