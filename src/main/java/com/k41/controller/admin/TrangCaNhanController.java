package com.k41.controller.admin;

import com.k41.config.PasswordEncript;
import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.entity.NguoiDung;
import com.k41.service.NguoiDungService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/admin/trangcanhan")
public class TrangCaNhanController {
    @Autowired
    private TaiKhoan taiKhoan;
    @Autowired
    private NguoiDungService nguoiDungService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String loadTrangCaNhan(Model model) {
        loadTen(model);
        NguoiDung nguoiDung = taiKhoan.getTaiKhoanDangNhap();
        log.info(nguoiDung.toString());
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/TrangCaNhan/TrangCaNhan";
    }

    @RequestMapping(value = { "/update"}, method = RequestMethod.POST)
    public String capNhatTaiKhoan(Model model, HttpServletRequest request) throws ParseException {
        String back = request.getHeader("Referer");
        String tenDangNhap = request.getParameter("tenDangNhap");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("diaChi");
        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");
        String gioiTinh = request.getParameter("gioiTinh");


        NguoiDung nguoiDung = taiKhoan.getTaiKhoanDangNhap();
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setHoTen(hoTen);
        log.info(ngaySinh);

        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setGioiTinh(Boolean.valueOf(gioiTinh));
        if(!ObjectUtils.isEmpty(ngaySinh)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStr = formatter.parse(ngaySinh);
            dateStr.setHours(23);
            dateStr.setMinutes(59);
            nguoiDung.setNgaySinh(dateStr.toInstant());
        }

        if(!ObjectUtils.isEmpty(matKhau)) {
            nguoiDung.setMatKhau(PasswordEncript.encrytePassword(matKhau));
        }
        nguoiDungService.save(nguoiDung);
        return "redirect:/admin/trangcanhan";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activetrangcanhan, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }

}
