package com.k41.controller.admin;

import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.entity.NguoiDung;
import com.k41.entity.TonVinh;
import com.k41.service.TonVinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/lichsuvinhdanh")
public class LichSuVinhDanh {
    @Autowired
    private TonVinhService tonVinhService;
    @Autowired
    private TaiKhoan taiKhoan;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String loadTrangQuanLyTaiKhoan(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "year", required = false) Integer year, Model model) {

        loadTen(model);
        if (ObjectUtils.isEmpty(page)) page = 1;
        if (ObjectUtils.isEmpty(year)) year = Calendar.getInstance().get(Calendar.YEAR);
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE);
        Page<TonVinh> pageable = tonVinhService.findPage(year, paging);

        List<TonVinh> tonVinhs = pageable.getContent();
        model.addAttribute("tonVinhs", tonVinhs);
        model.addAttribute("totalPage", pageable.getTotalPages());
        model.addAttribute("year", year);
        return "admin/LichSuVinhDanh/LichSuVinhDanh";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public String themTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        return "redirect:" + back;
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String capNhatTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        int idphong = Integer.parseInt(request.getParameter("idphong"));

        return "redirect:" + back;
    }

    @RequestMapping(value = {"/disable"}, method = RequestMethod.POST)
    public String xoaTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        int idphong = Integer.parseInt(request.getParameter("idphong"));

        return "redirect:" + back;
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activelichsuvinhdanh, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }
}
