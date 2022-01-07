package com.k41.excel;

import com.k41.entity.TonVinh;
import com.k41.repository.TonVinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// dịch vụ sử dụng xuất file excel
@Service
public class ExportService {
    private static final List<String> header = Arrays.asList("ID vinh danh", "Họ và tên", "Cơ sở hiến máu", "Ngày sinh", "Địa chỉ",
            "Số điện thoại", "Email", "CMND/CCCD", "Giới tính", "Nhóm máu", "Ngày vinh danh", "Mức vinh danh", "Số lần hiến");

    @Autowired
    private TonVinhRepository tonVinhRepository;

    public void exportExcelFile(HttpServletResponse response, int year) throws Exception {
        List<TonVinh> tonVinhs = tonVinhRepository.findAllByYear(year);
        List<ExportDTO> exportDTOS = new ArrayList<>();
        ExportDTO exportDTO;
        for (TonVinh tonVinh : tonVinhs) {
            exportDTO = new ExportDTO();
            exportDTO.setId(tonVinh.getId().toString());
            exportDTO.setCmndOrCccd(tonVinh.getNguoiHienMau().getCmndOrCccd());
            exportDTO.setEmail(tonVinh.getNguoiHienMau().getEmail());
            exportDTO.setDiaChi(tonVinh.getNguoiHienMau().getDiaChi());
            exportDTO.setCoSo(tonVinh.getNguoiHienMau().getCoSo().getTenCoSo());
            exportDTO.setNgaySinh(tonVinh.getNguoiHienMau().getNgaySinh().toString().substring(0,10));
            exportDTO.setMucVinhDanh(String.valueOf(tonVinh.getMucTonVinh()));
            exportDTO.setNhomMau(tonVinh.getNguoiHienMau().getNhomMau());
            exportDTO.setHoTen(tonVinh.getNguoiHienMau().getHoTen());
            exportDTO.setNgayVinhDanh(tonVinh.getNgayTonVinh().toString().substring(0,10));
            exportDTO.setSoLanHien(String.valueOf(tonVinh.getNguoiHienMau().getSoLanHienMau()));
            exportDTO.setSoDienThoai(tonVinh.getNguoiHienMau().getSoDienThoai());
            if (tonVinh.getNguoiHienMau().isGioiTinh()) {
                exportDTO.setGioiTinh("Nam");
            } else {
                exportDTO.setGioiTinh("Nữ");
            }
            exportDTOS.add(exportDTO);
        }

        try {
            Export<ExportDTO> excelExporter = new Export<>("VinhDanh", header);
            excelExporter.generateTemplate();
            excelExporter.fillData(exportDTOS);
            excelExporter.exportFile(response);
        } catch (Exception e) {
            throw e;
        }

    }
}
