package com.k41.controller;

import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.service.TonVinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/home"})
public class HomeController {
    @Autowired
    private TaiKhoan taiKhoan;
    @Autowired
    private TonVinhService tonVinhService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePge(Model model) {
        loadTen(model);
        return "home";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activetrangchu, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }
}
