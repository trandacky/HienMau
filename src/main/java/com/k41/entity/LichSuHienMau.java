package com.k41.entity;

import javax.persistence.*;
import java.time.Instant;

// class để mapping bảng trong database
@Entity
@Table(name = "lich_su_hien_mau")
public class LichSuHienMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nguoi_hien_mau_id", nullable = false)
    private NguoiHienMau nguoiHienMau;

    @Column(name = "ml")
    private Double ml;

    @Column(name = "ngay_hien_mau")
    private Instant ngayHienMau = Instant.now();

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

    public Double getMl() {
        return ml;
    }

    public void setMl(Double ml) {
        this.ml = ml;
    }

    public Instant getNgayHienMau() {
        return ngayHienMau;
    }

    public void setNgayHienMau(Instant ngayHienMau) {
        this.ngayHienMau = ngayHienMau;
    }
}
