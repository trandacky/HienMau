package com.k41.excel;

public class ImportDTO {
    private String hoTen;
    private String ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private String cmndOrCccd;
    private String gioiTinh;
    private String nhomMau;
    private String ngayHien;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getCmndOrCccd() {
        return cmndOrCccd;
    }

    public void setCmndOrCccd(String cmndOrCccd) {
        this.cmndOrCccd = cmndOrCccd;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNhomMau() {
        return nhomMau;
    }

    public void setNhomMau(String nhomMau) {
        this.nhomMau = nhomMau;
    }

    public String getNgayHien() {
        return ngayHien;
    }

    public void setNgayHien(String ngayHien) {
        this.ngayHien = ngayHien;
    }

    @Override
    public String toString() {
        return "ImportDTO{" +
                "hoTen='" + hoTen + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", cmndOrCccd='" + cmndOrCccd + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", nhomMau='" + nhomMau + '\'' +
                ", ngayHien='" + ngayHien + '\'' +
                '}';
    }
}
