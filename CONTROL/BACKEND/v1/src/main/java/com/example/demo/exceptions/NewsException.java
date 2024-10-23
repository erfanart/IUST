package com.example.demo.exceptions;

public class NewsException extends IllegalArgumentException {
    public NewsException(String s) {
        super(s);
    }

    public NewsException() {
    }
}
