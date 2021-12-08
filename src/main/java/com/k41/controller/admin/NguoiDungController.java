package com.k41.controller.admin;

import com.k41.constants.PageConstant;
import com.k41.entity.NguoiDung;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/admin/taikhoan")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String loadTrangQuanLyTaiKhoan(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        if (ObjectUtils.isEmpty(page)) page = 1;
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE);
        Page<NguoiDung> pageable = nguoiDungService.findPage(paging);

        List<NguoiDung> nguoiDungs = pageable.getContent();
        model.addAttribute("nguoiDungs", "nguoiDungs");
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "/admin/QuanLyTaiKhoan/QuanLyTaiKhoan";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public String themTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        return "redirect:" + back;
    }

    @RequestMapping(value = { "/update"}, method = RequestMethod.POST)
    public String capNhatTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        int idphong = Integer.parseInt(request.getParameter("idphong"));

        return "redirect:" + back;
    }

    @RequestMapping(value = { "/disable"}, method = RequestMethod.POST)
    public String xoaTaiKhoan(Model model, HttpServletRequest request) {
        String back = request.getHeader("Referer");
        int idphong = Integer.parseInt(request.getParameter("idphong"));

        return "redirect:" + back;
    }
}
