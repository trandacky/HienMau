package com.k41.controller.admin;

import com.k41.config.PasswordEncript;
import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
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

@Controller
@RequestMapping("/admin")
public class AdminDoiMatKhau {
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private FogotPasswordService fogotPasswordService;

    @Autowired
    private TaiKhoan taiKhoan;

    @RequestMapping(value = {"/quen-mat-khau"},method = RequestMethod.GET)
    public String quenMatKhau(Model model) throws UnknownHostException, MessagingException {
        loadTen(model);
        model.addAttribute(PageActive.activedoimatkhau, PageActive.active);
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(taiKhoan.getTaiKhoanDangNhap().getTenDangNhap());
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

    @RequestMapping(value = {"/quen-mat-khau/{uuid}"},method = RequestMethod.GET)
    public String loadQuenMatKhau(@PathVariable String uuid, Model model) {
        loadTen(model);
        model.addAttribute(PageActive.activedoimatkhau, PageActive.active);
        model.addAttribute("uuid", uuid);
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByActiveKey(uuid);
        if(!nguoiDung.isPresent()) {
            model.addAttribute("message", "Link đã được sử dụng");
            model.addAttribute("alert", "danger");
        }
        return "/user/QuenMatKhau";
    }

    @RequestMapping(value = {"/quen-mat-khau/{uuid}"},method = RequestMethod.POST)
    public String doiMatKhau(@PathVariable String uuid, Model model, HttpServletRequest request) {
        loadTen(model);
        model.addAttribute(PageActive.activedoimatkhau, PageActive.active);
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
        return "redirect:/home";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }
}
