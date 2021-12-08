package com.k41.controller;

import com.k41.constants.PageConstant;
import com.k41.entity.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class Public {
    @RequestMapping(method = RequestMethod.GET)
    public String loadTrangQuanLyTaiKhoan() {
        return "/null";
    }
}
