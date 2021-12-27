package com.k41.service;

import com.k41.entity.DeXuatTonVinh;
import com.k41.entity.NguoiHienMau;
import com.k41.repository.DeXuatTonVinhRepository;
import com.k41.repository.LichSuHienMauRepository;
import com.k41.repository.NguoiHienMauRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class DeXuatTonVinhScheduleService {
    private boolean isSchedule = true;

    @Autowired
    private DeXuatTonVinhRepository deXuatTonVinhRepository;
    @Autowired
    private NguoiHienMauRepository nguoiHienMauRepository;
    @Autowired
    private LichSuHienMauRepository lichSuHienMauRepository;


    @Scheduled(fixedRate = 10000)
    public void run() {
        if (!isSchedule) {
            log.info("Disable ton vinh schedule");
            return;
        }
        log.info("Run de xuat ton vinh schedule");
        syncNguoiHienMau();
        int capDoTonVinh;
        Instant ngayDeXuat = Instant.now();
        List<DeXuatTonVinh> deXuatTonVinhs = deXuatTonVinhRepository.findAll();
        List<DeXuatTonVinh> deXuatTonVinhMoi = new ArrayList<>();
        List<NguoiHienMau> nguoiHienMaus = nguoiHienMauRepository.findDeXuatTonVinh();
        DeXuatTonVinh deXuatTonVinh;
        for (NguoiHienMau nguoiHienMau : nguoiHienMaus) {
            capDoTonVinh = (nguoiHienMau.getSoLanHienMau()/5);
            if(capDoTonVinh*5<(nguoiHienMau.getSoLanHienMau()/5)+1) {
                continue;
            }
            capDoTonVinh = (nguoiHienMau.getSoLanHienMau()/5);
            deXuatTonVinh = new DeXuatTonVinh();
            deXuatTonVinh.setNgayTonVinh(ngayDeXuat);
            deXuatTonVinh.setChapNhanTonVinh(false);
            deXuatTonVinh.setNguoiHienMau(nguoiHienMau);
            capDoTonVinh = (nguoiHienMau.getSoLanHienMau()/5);
            deXuatTonVinh.setMucTonVinh(capDoTonVinh * 5);
            deXuatTonVinhMoi.add(deXuatTonVinh);
            for (int i = 0; i < deXuatTonVinhs.size(); i++) {
                if (deXuatTonVinhs.get(i).getNguoiHienMau().getId() == nguoiHienMau.getId()||deXuatTonVinhs.get(i).isEdit()) {
                    deXuatTonVinhMoi.remove(deXuatTonVinh);
                    deXuatTonVinh= deXuatTonVinhs.get(i);
                    capDoTonVinh = (nguoiHienMau.getSoLanHienMau()/5);
                    deXuatTonVinh.setMucTonVinh(capDoTonVinh * 5);
                    deXuatTonVinh.setNgayTonVinh(ngayDeXuat);
                    deXuatTonVinhMoi.add(deXuatTonVinh);
                    deXuatTonVinhs.remove(i);
                    i--;
                    break;
                }
            }
        }
        deXuatTonVinhRepository.deleteAll(deXuatTonVinhs);
        deXuatTonVinhRepository.saveAll(deXuatTonVinhMoi);

    }

    private void syncNguoiHienMau() {
        int soLanHien;
        List<NguoiHienMau> nguoiHienMaus = nguoiHienMauRepository.findAll();
        for (NguoiHienMau nguoiHienMau : nguoiHienMaus) {
            soLanHien = lichSuHienMauRepository.findByNguoiHienMau(nguoiHienMau).size();
            nguoiHienMau.setSoLanHienMau(soLanHien);
            nguoiHienMauRepository.save(nguoiHienMau);
        }
    }

    public boolean isSchedule() {
        return isSchedule;
    }

    public void setSchedule(boolean schedule) {
        isSchedule = schedule;
    }
}
