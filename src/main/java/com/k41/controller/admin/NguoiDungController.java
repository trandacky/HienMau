package com.k41.controller.admin;

import com.k41.config.PasswordEncript;
import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.constants.QuyenConstants;
import com.k41.entity.NguoiDung;
import com.k41.entity.Quyen;
import com.k41.entity.QuyenNguoiDung;
import com.k41.service.NguoiDungService;
import com.k41.service.QuyenNguoiDungService;
import com.k41.service.QuyenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


// class để quản lý tài khoản
@Controller
@Slf4j
@RequestMapping(value = "/admin/taikhoan")
public class NguoiDungController {
    @Autowired
    private TaiKhoan taiKhoan;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private QuyenService quyenService;
    @Autowired
    private QuyenNguoiDungService quyenNguoiDungService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String loadTrangQuanLyTaiKhoan(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        loadTen(model);
        if (ObjectUtils.isEmpty(page)) page = 1;
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE_QUAN_LY_TAI_KHOAN, Sort.by("id").descending());
        Page<NguoiDung> pageable = nguoiDungService.findPage(paging);

        List<NguoiDung> nguoiDungs = pageable.getContent();
        model.addAttribute("nguoiDungs", nguoiDungs);
        model.addAttribute("them", "them");
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "admin/QuanLyTaiKhoan/QuanLyTaiKhoan";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public String themTaiKhoan(Model model, HttpServletRequest request) throws ParseException {
        model.addAttribute("them", "them");
        String tenDangNhap = request.getParameter("tenDangNhap");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("diaChi");
        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");
        String gioiTinh = request.getParameter("gioiTinh");

        Optional<NguoiDung> nguoiDungOption = nguoiDungService.findByTenDangNhapOrEmailLike(tenDangNhap);
        Optional<NguoiDung> nguoiDungOption2 = nguoiDungService.findByTenDangNhapOrEmailLike(email);
        if(nguoiDungOption.isPresent()||nguoiDungOption2.isPresent()) {
            model.addAttribute("message", "Tên đăng nhập hoặc email đã tồn tại");
            model.addAttribute("alert", "danger");
            Pageable paging = PageRequest.of(0, PageConstant.PAGE_SIZE_QUAN_LY_TAI_KHOAN, Sort.by("id").descending());
            Page<NguoiDung> pageable = nguoiDungService.findPage(paging);

            List<NguoiDung> nguoiDungs = pageable.getContent();
            model.addAttribute("nguoiDungs", nguoiDungs);
            model.addAttribute("totalPage", pageable.getTotalPages());
            return "admin/QuanLyTaiKhoan/QuanLyTaiKhoan";
        }
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(tenDangNhap);
        nguoiDung.setEmail(email);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setGioiTinh(Boolean.valueOf(gioiTinh));
        nguoiDung.setActive(true);
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
        nguoiDung = nguoiDungService.save(nguoiDung);
        Quyen quyen = quyenService.findByRole(QuyenConstants.ADMIN.getRole());
        QuyenNguoiDung quyenNguoiDung = new QuyenNguoiDung();
        quyenNguoiDung.setNguoiDung(nguoiDung);
        quyenNguoiDung.setQuyen(quyen);
        quyenNguoiDungService.save(quyenNguoiDung);
        return "redirect:/admin/taikhoan";
    }

    @RequestMapping(value = {"/active/{tendangnhap}"}, method = RequestMethod.GET)
    public String xoaTaiKhoan(@PathVariable String tendangnhap) {
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(tendangnhap).get();
        if (ObjectUtils.isEmpty(nguoiDung.getActive())) {
            nguoiDung.setActive(false);
        }
        nguoiDung.setActive(!nguoiDung.getActive());
        nguoiDungService.save(nguoiDung);
        return "redirect:/admin/taikhoan";
    }

    @RequestMapping(value = {"/capnhat/{tendangnhap}"}, method = RequestMethod.GET)
    public String getCapNhatTaiKhoan(@PathVariable String tendangnhap, Model model, HttpServletRequest request) {
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(tendangnhap).get();
        Pageable paging = PageRequest.of(0, PageConstant.PAGE_SIZE_QUAN_LY_TAI_KHOAN, Sort.by("id").descending());
        Page<NguoiDung> pageable = nguoiDungService.findPage(paging);

        List<NguoiDung> nguoiDungs = pageable.getContent();
        model.addAttribute("sua", "sua");
        model.addAttribute("nguoiDungs", nguoiDungs);
        model.addAttribute("totalPage", pageable.getTotalPages());
        model.addAttribute("nguoiDung", nguoiDung);

        return "admin/QuanLyTaiKhoan/QuanLyTaiKhoan";
    }

    @RequestMapping(value = {"/capnhat/{tendangnhap}"}, method = RequestMethod.POST)
    public String capNhatTaiKhoan(@PathVariable String tendangnhap, Model model, HttpServletRequest request) throws ParseException {
        loadTen(model);
        model.addAttribute("them", "them");
        String tenDangNhap = request.getParameter("tenDangNhap");
        String email = request.getParameter("email");
        String diaChi = request.getParameter("diaChi");
        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");
        String gioiTinh = request.getParameter("gioiTinh");

        Optional<NguoiDung> nguoiDungOption = nguoiDungService.findByTenDangNhapOrEmailLike(tenDangNhap);
        Optional<NguoiDung> nguoiDungOption2 = nguoiDungService.findByTenDangNhapOrEmailLike(email);
        if(!nguoiDungOption.isPresent()||!nguoiDungOption2.isPresent()) {
            model.addAttribute("message", "Tên đăng nhập hoặc email đã tồn tại");
            model.addAttribute("alert", "danger");
            Pageable paging = PageRequest.of(0, PageConstant.PAGE_SIZE_QUAN_LY_TAI_KHOAN, Sort.by("id").descending());
            Page<NguoiDung> pageable = nguoiDungService.findPage(paging);

            List<NguoiDung> nguoiDungs = pageable.getContent();
            model.addAttribute("nguoiDungs", nguoiDungs);
            model.addAttribute("totalPage", pageable.getTotalPages());
            return "admin/QuanLyTaiKhoan/QuanLyTaiKhoan";
        }
        NguoiDung nguoiDung = nguoiDungOption.get();
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setGioiTinh(Boolean.valueOf(gioiTinh));
        nguoiDung.setActive(nguoiDung.getActive());
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
        nguoiDung = nguoiDungService.save(nguoiDung);
        return "redirect:/admin/taikhoan";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activetaikhoan, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }

}
