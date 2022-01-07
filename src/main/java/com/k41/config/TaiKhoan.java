package com.k41.config;

import com.k41.entity.NguoiDung;
import com.k41.service.NguoiDungService;
import com.k41.service.implement.NguoiDungImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//lấy thông tin tài khoản khi người dùng đăng nhập
@Component
public class TaiKhoan {

    @Autowired
    private  NguoiDungService nguoiDungService;

    public NguoiDung getTaiKhoanDangNhap() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        NguoiDung taiKhoan = new NguoiDung();
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(username);
        if (nguoiDung.isPresent()) {
            return nguoiDung.get();
        }
        return taiKhoan;
    }
}
