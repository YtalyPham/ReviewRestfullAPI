package com.example.reviewRestfullAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


public class ErrorDetails   {
    private LocalDateTime timestamp;
    private String errorCode;
    private String detail;

    public ErrorDetails(LocalDateTime timestamp, String errorCode, String detail) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDetail() {
        return detail;
    }
}
