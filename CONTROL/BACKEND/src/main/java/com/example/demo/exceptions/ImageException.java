package com.example.demo.exceptions;

public class ImageException extends IllegalArgumentException{
    public ImageException() {
    }

    public ImageException(String s) {
        super(s);
    }
}
