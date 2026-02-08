package com.example.livetalk.common.advice;

import com.example.livetalk.common.enums.ExceptionCode;
import com.example.livetalk.model.LiveTalkException;
import com.example.livetalk.model.resp.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception exception, HttpServletRequest request) {
        log.error("server error {} {}", request.getMethod(), request.getRequestURI(),exception);
        return ResponseEntity.internalServerError().body(CommonResponse.error(ExceptionCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = {LiveTalkException.class})
    public ResponseEntity<?> exceptionHandler(LiveTalkException exception) {
        return ResponseEntity.status(exception.getExceptionCode().getStatus())
                .body(CommonResponse.error(exception.getExceptionCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream().findFirst().map(FieldError::getDefaultMessage).orElse("요청 값이 올바르지 않습니다.");
        return ResponseEntity.badRequest().body(CommonResponse.error(String.valueOf(status.value()), errorMessage, null));
    }

}
