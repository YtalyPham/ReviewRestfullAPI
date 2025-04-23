package com.example.reviewRestfullAPI.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
      name="hoadon",
      indexes = {
              @Index(name="hoadon_idx", columnList = "maHoaDon,ngayLapHoaDon")
      }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoaDon;

    @Column(name="ngayLapHoaDon")
    private LocalDateTime ngayLapHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="maKhachHang")
    private KhachHang khachHangHD;


}
