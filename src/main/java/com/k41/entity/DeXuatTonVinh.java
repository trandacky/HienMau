package com.k41.entity;

import javax.persistence.*;
import java.time.Instant;

// class để mapping bảng trong database
@Entity
@Table(name = "de_xuat_ton_vinh")
public class DeXuatTonVinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nguoi_hien_mau_id", nullable = false)
    private NguoiHienMau nguoiHienMau;

    @Column(name = "muc_ton_vinh")
    private int mucTonVinh;

    @Column(name = "ngay_ton_vinh")
    private Instant ngayTonVinh = Instant.now();

    @Column(name = "chap_nhan_ton_vinh")
    private boolean chapNhanTonVinh = false;

    @Column(name = "is_edit")
    private boolean isEdit = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NguoiHienMau getNguoiHienMau() {
        return nguoiHienMau;
    }

    public void setNguoiHienMau(NguoiHienMau nguoiHienMau) {
        this.nguoiHienMau = nguoiHienMau;
    }

    public int getMucTonVinh() {
        return mucTonVinh;
    }

    public void setMucTonVinh(int mucTonVinh) {
        this.mucTonVinh = mucTonVinh;
    }

    public Instant getNgayTonVinh() {
        return ngayTonVinh;
    }

    public void setNgayTonVinh(Instant ngayTonVinh) {
        this.ngayTonVinh = ngayTonVinh;
    }

    public boolean isChapNhanTonVinh() {
        return chapNhanTonVinh;
    }

    public void setChapNhanTonVinh(boolean chapNhanTonVinh) {
        this.chapNhanTonVinh = chapNhanTonVinh;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
