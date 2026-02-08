package com.example.livetalk.model;

import com.example.livetalk.common.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class LiveTalkException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public LiveTalkException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
