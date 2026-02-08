package com.example.livetalk.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    DUPLICATE_MEMBER_ID(HttpStatus.CONFLICT, String.valueOf(HttpStatus.CONFLICT.value()), "이미 사용 중인 아이디입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()), "존재하지 않는 회원입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),"서버 오류가 발생했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
