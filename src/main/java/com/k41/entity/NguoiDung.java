package com.k41.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 100, name = "ten_dang_nhap", unique = true)
    private String tenDangNhap;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ngay_sinh")
    private Instant ngaySinh;

    @Column(name = "que_quan")
    private String queQuan;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "active_key")
    private String activeKey;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "nguoiDung")
    private List<QuyenNguoiDung> quyenNguoiDungs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Instant getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Instant ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoaiLienHe) {
        this.soDienThoai = soDienThoaiLienHe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getActiveKey() {
        return activeKey;
    }

    public void setActiveKey(String activeKey) {
        this.activeKey = activeKey;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<QuyenNguoiDung> getQuyenNguoiDungs() {
        return quyenNguoiDungs;
    }

    public void setQuyenNguoiDungs(List<QuyenNguoiDung> quyenNguoiDungs) {
        this.quyenNguoiDungs = quyenNguoiDungs;
    }
}
