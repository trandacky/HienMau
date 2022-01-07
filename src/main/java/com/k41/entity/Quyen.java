package com.k41.entity;

import javax.persistence.*;
import java.util.List;

// class để mapping bảng trong database
@Entity
@Table(name = "quyen")
public class Quyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", unique = true)
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quyen")
    private List<QuyenNguoiDung> quyenNguoiDungs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<QuyenNguoiDung> getQuyenNguoiDungs() {
        return quyenNguoiDungs;
    }

    public void setQuyenNguoiDungs(List<QuyenNguoiDung> quyenNguoiDungs) {
        this.quyenNguoiDungs = quyenNguoiDungs;
    }
}
