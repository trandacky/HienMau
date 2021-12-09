package com.k41.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "nguoi_hien_mau")
public class NguoiHienMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_so_id", nullable = false)
    private CoSo coSo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nguoiHienMau")
    private List<TonVinh> tonVinhs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nguoiHienMau")
    private List<DeXuatTonVinh> deXuatTonVinhs;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "ngaySinh")
    private Instant ngaySinh;

    @Column(name = "cmnd_or_cccd")
    private String cmndOrCccd;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "muc_ton_vinh")
    private int mucTonVinh;

    @Column(name = "so_lan_hien_mau")
    private int soLanHienMau;

    @Column(name = "nguoi_tao", nullable = false, length = 100, updatable = false)
    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @Column(name = "nguoi_cap_nhat", length = 100)
    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;

    @CreatedDate
    @Column(name = "ngay_tao", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    @Column(name = "lan_hien_gan_nhat")
    private Instant lanHienGanNhat = Instant.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoSo getCoSo() {
        return coSo;
    }

    public void setCoSo(CoSo coSo) {
        this.coSo = coSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Instant ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmndOrCccd() {
        return cmndOrCccd;
    }

    public void setCmndOrCccd(String cmndOrCccd) {
        this.cmndOrCccd = cmndOrCccd;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<TonVinh> getTonVinhs() {
        return tonVinhs;
    }

    public void setTonVinhs(List<TonVinh> tonVinhs) {
        this.tonVinhs = tonVinhs;
    }

    public List<DeXuatTonVinh> getDeXuatTonVinhs() {
        return deXuatTonVinhs;
    }

    public void setDeXuatTonVinhs(List<DeXuatTonVinh> deXuatTonVinhs) {
        this.deXuatTonVinhs = deXuatTonVinhs;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMucTonVinh() {
        return mucTonVinh;
    }

    public void setMucTonVinh(int mucTonVinh) {
        this.mucTonVinh = mucTonVinh;
    }

    public int getSoLanHienMau() {
        return soLanHienMau;
    }

    public void setSoLanHienMau(int soLanHienMau) {
        this.soLanHienMau = soLanHienMau;
    }

    public Instant getLanHienGanNhat() {
        return lanHienGanNhat;
    }

    public void setLanHienGanNhat(Instant lanHienGanNhat) {
        this.lanHienGanNhat = lanHienGanNhat;
    }
}
