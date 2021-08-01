package com.example.my.little.bank.MyException;

public class NotFoundException extends Exception{
    public NotFoundException(Long id) {
        super("Unknown id " + id.toString());
    }
}
