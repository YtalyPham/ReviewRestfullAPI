package com.example.reviewRestfullAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateDataException extends Exception{
    private static final long serialVersionUID = 1L;

    public DuplicateDataException(String message){
        super(message);
    }
}
