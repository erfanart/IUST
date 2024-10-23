package com.example.demo.exceptions;

public class RoleException extends IllegalArgumentException{
    public RoleException() {
    }

    public RoleException(String s) {
        super(s);
    }
}
