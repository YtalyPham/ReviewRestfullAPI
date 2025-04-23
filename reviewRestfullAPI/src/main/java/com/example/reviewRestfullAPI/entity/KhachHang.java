package com.example.reviewRestfullAPI.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity

@Table(
        name="khachhang",
        indexes = {
                @Index(name = "khachhang_idx", columnList="maKhachHang,tenKhachHang")
        }
)


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhachHang;

    @Column(name="tenKhachHang")

    private String tenKhachHang;

    @Column(name = "email")
    private String email;

    @Column(name ="sdt")
    private String sdt;

    @Column(name ="gioiTinh")
    private boolean gioiTinh;

    @Column(name ="ngaySinh")
    private LocalDateTime ngaySinh;

    @Column(name ="diemTichLuy")
    private int diemTichLuy;

    @Column(name ="vangLai")
    private boolean vangLai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHangHD")
    private List<HoaDon> hoaDons= new ArrayList<>();




}
