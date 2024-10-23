package com.example.demo.validation;

import com.example.demo.exceptions.ImageException;
import com.example.demo.exceptions.MailException;
import com.example.demo.exceptions.PasswordException;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    public static String userType = "";
    public void validatePassword(String password) throws PasswordException {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        boolean result = password.matches(regex);
        if (result != true) {
            throw new PasswordException("please Enter valid password(The password must have at least 8 characters and include numbers and upper and lower case letters)");
        }
    }

    public void validateMail(String mail) throws MailException {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[ir|com]+$";
        boolean result = mail.matches(regex);
        if (result != true) {
            throw new MailException("please enter valid email");
        }
    }

    public void validationImage(String type) {
        if (!type.equals("image/jpeg") && !type.equals("image/png")) {
            throw new ImageException("please upload image with 'jpeg' or 'png' format");
        }
    }
}
