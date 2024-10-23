package com.example.demo.exceptions;

public class AdminException extends IllegalArgumentException{
    public AdminException() {
    }

    public AdminException(String s) {
        super(s);
    }
}
