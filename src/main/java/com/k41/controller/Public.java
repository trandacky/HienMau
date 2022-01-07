package com.k41.controller;

import com.k41.config.PasswordEncript;
import com.k41.entity.NguoiDung;
import com.k41.service.FogotPasswordService;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Optional;

// class cho các trang không cần quyền để truy cập
@Controller
public class Public {
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private FogotPasswordService fogotPasswordService;
    @RequestMapping(value = {"/quen-mat-khau"},method = RequestMethod.GET)
    public String loadTrangQuenMatKhau() {
        return "/user/NhapEmailQuenMatKhau";
    }

    @RequestMapping(value = {"/quen-mat-khau"},method = RequestMethod.POST)
    public String quenMatKhau(Model model, HttpServletRequest request) throws UnknownHostException, MessagingException {
        String tenDangNhapOrEmail = request.getParameter("tenDangNhapOrEmail");
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(tenDangNhapOrEmail);
        if(!nguoiDung.isPresent()) {
            model.addAttribute("message", "Tên đăng nhập hoặc email không tồn tại");
            model.addAttribute("alert", "warning");
            return "/user/NhapEmailQuenMatKhau";
        }
        fogotPasswordService.sendEmail(nguoiDung.get());
        model.addAttribute("message", "Kiểm tra email của bạn");
        model.addAttribute("alert", "success");
        return "/user/NhapEmailQuenMatKhau";
    }

    // click vào link trong email sẽ trả về trang này
    @RequestMapping(value = {"/quen-mat-khau/{uuid}"},method = RequestMethod.GET)
    public String loadQuenMatKhau(@PathVariable String uuid, Model model) {
        model.addAttribute("uuid", uuid);
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByActiveKey(uuid);
        if(!nguoiDung.isPresent()) {
            model.addAttribute("message", "Link đã được sử dụng");
            model.addAttribute("alert", "danger");
        }
        return "/user/QuenMatKhau";
    }

    // khi bấm nút đổi email
    @RequestMapping(value = {"/quen-mat-khau/{uuid}"},method = RequestMethod.POST)
    public String doiMatKhau(@PathVariable String uuid, Model model, HttpServletRequest request) {
        String matKhauMoi = request.getParameter("matKhauMoi");
        String nhapLaiMatKhauMoi = request.getParameter("nhapLaiMatKhauMoi");

        Optional<NguoiDung> nguoiDung = nguoiDungService.findByActiveKey(uuid);
        if(!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
            model.addAttribute("message", "Mật Khẩu không đúng");
            model.addAttribute("alert", "warning");
            model.addAttribute("uuid", uuid);
            return "/user/QuenMatKhau";
        }
        if(!nguoiDung.isPresent()) {
            model.addAttribute("message", "Link đã được sử dụng");
            model.addAttribute("alert", "danger");
            return "/user/QuenMatKhau";
        } else {
            NguoiDung nguoiDung1 = nguoiDung.get();
            nguoiDung1.setActiveKey(null);
            nguoiDung1.setMatKhau(PasswordEncript.encrytePassword(matKhauMoi));
            nguoiDungService.save(nguoiDung1);
        }
        return "redirect:/";
    }
}
