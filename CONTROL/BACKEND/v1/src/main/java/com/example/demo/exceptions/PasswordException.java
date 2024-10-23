package com.example.demo.exceptions;

public class PasswordException extends IllegalArgumentException{
    public PasswordException() {
    }

    public PasswordException(String s) {
        super(s);
    }
}
