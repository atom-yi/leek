package com.cs314.leek.enums;

import lombok.Getter;

@Getter
public enum HttpStatus {
    SUCCESS(20000, "success"),
    FAILED(50001, "server internal exception");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
