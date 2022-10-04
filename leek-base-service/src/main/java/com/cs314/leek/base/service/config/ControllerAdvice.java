package com.cs314.leek.base.service.config;

import com.cs314.leek.vo.RestResp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Throwable.class)
    public RestResp<?> handleAll(Throwable e) {
        return new RestResp<>().failed(e.getMessage());
    }
}
