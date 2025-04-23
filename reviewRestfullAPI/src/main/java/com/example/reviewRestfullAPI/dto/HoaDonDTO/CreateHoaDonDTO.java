package com.example.reviewRestfullAPI.dto.HoaDonDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateHoaDonDTO {


    private LocalDateTime ngayLapHoaDon;

    private Long maKhachHang;

}
