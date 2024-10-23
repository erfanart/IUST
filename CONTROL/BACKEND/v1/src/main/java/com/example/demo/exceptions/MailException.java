package com.example.demo.exceptions;

public class MailException extends IllegalArgumentException {
    public MailException(String s) {
        super(s);
    }
    public MailException() {
    }
}
