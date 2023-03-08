package com.example.quanlybanhang.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
