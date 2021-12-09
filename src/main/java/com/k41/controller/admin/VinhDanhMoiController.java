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


@Controller
@Slf4j
@RequestMapping(value = "/admin/vinhdanhmoi")
public class VinhDanhMoiController {
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

        return "admin/VinhDanhMoi/VinhDanhMoi";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activevinhdanhmoi, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }

}
