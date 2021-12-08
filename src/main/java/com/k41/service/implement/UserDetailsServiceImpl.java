package com.k41.service.implement;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.k41.entity.NguoiDung;
import com.k41.entity.QuyenNguoiDung;
import com.k41.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NguoiDung> nguoiDung = nguoiDungService.findByTenDangNhapOrEmailLike(username);
        if (!nguoiDung.isPresent())
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        List<QuyenNguoiDung> quyenNguoiDungs = nguoiDung.get().getQuyenNguoiDungs();
        for (QuyenNguoiDung quyenNguoiDung : quyenNguoiDungs) {
            GrantedAuthority authority = new SimpleGrantedAuthority(quyenNguoiDung.getQuyen().getRole());
            grantList.add(authority);
        }

        UserDetails userDetails = (UserDetails) new User(username, nguoiDung.get().getMatKhau(), grantList);

        return userDetails;
    }

}