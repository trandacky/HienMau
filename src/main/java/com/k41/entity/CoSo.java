package com.k41.entity;

import javax.persistence.*;
import java.util.List;

// class để mapping bảng trong database
@Entity
@Table(name = "co_so")
public class CoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 100, name = "ten_co_so")
    private String tenCoSo;

    @Column(name = "tinh_trang_hoat_dong")
    private boolean tinhTrangHoatDong = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coSo")
    private List<NguoiHienMau> nguoiHienMaus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenCoSo() {
        return tenCoSo;
    }

    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }

    public List<NguoiHienMau> getNguoiHienMaus() {
        return nguoiHienMaus;
    }

    public void setNguoiHienMaus(List<NguoiHienMau> nguoiHienMaus) {
        this.nguoiHienMaus = nguoiHienMaus;
    }

    public boolean isTinhTrangHoatDong() {
        return tinhTrangHoatDong;
    }

    public void setTinhTrangHoatDong(boolean tinhTrangHoatDong) {
        this.tinhTrangHoatDong = tinhTrangHoatDong;
    }
}
