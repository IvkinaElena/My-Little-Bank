package com.example.my.little.bank.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Unknown id " + id.toString());
    }
}
