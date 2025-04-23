package com.example.reviewRestfullAPI.dto.KhachHangDTO;


import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateKhachHangDTO {


    private String tenKhachHang;

    private String email;

    private String sdt;

    private boolean gioiTinh;

    private LocalDateTime ngaySinh;

    private int diemTichLuy;

    private boolean vangLai;
}
