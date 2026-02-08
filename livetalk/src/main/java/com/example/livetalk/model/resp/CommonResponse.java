package com.example.livetalk.model.resp;

import com.example.livetalk.common.enums.ExceptionCode;


public record CommonResponse<T>(
        String code,
        String message,
        T data
) {
    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>("0", "", data);
    }

    public static <T> CommonResponse<T> ok(String message, T data) {
        return new CommonResponse<>("0", message, data);
    }

    public static <T> CommonResponse<T> error(String code, String message, T data) {
        return new CommonResponse<>(code, message, data);
    }

    public static <T> CommonResponse<T> error(ExceptionCode exceptionCode) {
        return new CommonResponse<>(exceptionCode.getCode(), exceptionCode.getMessage(), null);
    }

}
