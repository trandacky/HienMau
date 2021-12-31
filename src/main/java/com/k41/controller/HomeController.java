package com.k41.controller;

import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.entity.TonVinh;
import com.k41.service.TonVinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = {"/home"})
public class HomeController {
    @Autowired
    private TaiKhoan taiKhoan;
    @Autowired
    private TonVinhService tonVinhService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePge(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "year", required = false) Integer year, Model model) {
        loadTen(model);
        if (ObjectUtils.isEmpty(page)) page = 1;
        if (ObjectUtils.isEmpty(year)) year = Calendar.getInstance().get(Calendar.YEAR);
        Pageable paging = PageRequest.of(page - 1, PageConstant.VINH_DANH_SIZE, Sort.Direction.DESC, "mucTonVinh", "ngayTonVinh");
        Page<TonVinh> pageable = tonVinhService.findPage(year, paging);

        List<TonVinh> tonVinhs = pageable.getContent();
        model.addAttribute("tonVinhs", tonVinhs);
        model.addAttribute("totalPage", pageable.getTotalPages());
        model.addAttribute("year", year);
        return "home";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activetrangchu, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }
}
