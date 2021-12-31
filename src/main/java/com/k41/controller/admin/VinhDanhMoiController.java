package com.k41.controller.admin;

import com.k41.config.PasswordEncript;
import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.constants.QuyenConstants;
import com.k41.entity.*;
import com.k41.service.*;
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
    @Autowired
    private DeXuatTonVinhScheduleService deXuatTonVinhScheduleService;
    @Autowired
    private DeXuatTonVinhService deXuatTonVinhService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String loadTrangQuanLyTaiKhoan(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        loadTen(model);
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE, Sort.by("mucTonVinh").descending());
        Page<DeXuatTonVinh> pageable = deXuatTonVinhService.findPage(paging);
        List<DeXuatTonVinh> deXuatTonVinhs = pageable.getContent();
        model.addAttribute("totalPage", pageable.getTotalPages());
        model.addAttribute("deXuatTonVinhs", deXuatTonVinhs);
        model.addAttribute("tuDongDeXuat", deXuatTonVinhScheduleService.isSchedule());
        return "admin/VinhDanhMoi/VinhDanhMoi";
    }
    @RequestMapping(value = { "/sync"}, method = RequestMethod.GET)
    public String notSync(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        loadTen(model);
        deXuatTonVinhScheduleService.setSchedule(!deXuatTonVinhScheduleService.isSchedule());
        return "redirect:/admin/vinhdanhmoi";
    }

    @RequestMapping(value = {"/tontinh/submit"}, method = RequestMethod.GET)
    public String uploadVinhDanh(Model model) {
        loadTen(model);
        deXuatTonVinhScheduleService.setSchedule(false);
        deXuatTonVinhService.deXuat();
        Pageable paging = PageRequest.of(0, PageConstant.PAGE_SIZE, Sort.by("mucTonVinh").descending());
        Page<DeXuatTonVinh> pageable = deXuatTonVinhService.findPage(paging);
        List<DeXuatTonVinh> deXuatTonVinhs = pageable.getContent();
        deXuatTonVinhScheduleService.setSchedule(true);
        model.addAttribute("totalPage", pageable.getTotalPages());
        model.addAttribute("deXuatTonVinhs", deXuatTonVinhs);
        model.addAttribute("tuDongDeXuat", deXuatTonVinhScheduleService.isSchedule());
        return "admin/VinhDanhMoi/VinhDanhMoi";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activevinhdanhmoi, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }

}
