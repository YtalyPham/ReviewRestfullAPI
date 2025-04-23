package com.example.reviewRestfullAPI.dto.HoaDonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewHoaDonDTO {
    private Long maHoaDon;

    private LocalDateTime ngayLapHoaDon;

    private Long maKhachHangHD;
}
