package com.livecode.insurance.utils;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BaseResponseDTO<T> {
    private int status;
    private String message;
    private T data;
    private Date timestamp = new Date();

    public BaseResponseDTO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
