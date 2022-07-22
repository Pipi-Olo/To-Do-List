package com.pipiolo.itemshop.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity GlobalExHandler(GlobalException e) {
        ErrorResult errorResult = new ErrorResult(e.getMessage());
        return new ResponseEntity<>(errorResult, e.getErrorCode().getHttpStatus());
    }
}
