package com.example.demo.exceptions;

public class FormException extends IllegalArgumentException {

    public FormException() {
    }

    public FormException(String s) {
        super(s);
    }
}
