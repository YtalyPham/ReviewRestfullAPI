package com.example.reviewRestfullAPI.dto.ResponseDTO;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;
}
