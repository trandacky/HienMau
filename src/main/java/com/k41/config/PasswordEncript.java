package com.k41.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// encode mật khẩu khi lưu
public class PasswordEncript {
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
