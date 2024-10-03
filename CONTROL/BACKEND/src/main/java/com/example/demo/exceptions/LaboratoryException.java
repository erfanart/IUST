package com.example.demo.exceptions;

public class LaboratoryException extends IllegalArgumentException{
    public LaboratoryException() {
    }

    public LaboratoryException(String s) {
        super(s);
    }
}
