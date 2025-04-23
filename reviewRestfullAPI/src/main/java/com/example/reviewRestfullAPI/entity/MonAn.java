package com.example.reviewRestfullAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "monan",
        indexes = {
                @Index(name = "monan_idx", columnList = "maMonAn, tenMonAn")
        }

)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maMonAn;

    @Column(name="tenMonAn")
    private String tenMonAn;

    @Column(name="donGia")
    private Double donGia;

}
