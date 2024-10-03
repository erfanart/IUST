package com.example.demo.services;

import com.example.demo.dto.AdminDto;
import com.example.demo.security.config.AuthenticationResponse;

import java.util.List;

public interface SuperAdminService {
    AuthenticationResponse authenticate(String username, String password);
    void changePassword(String password, String username);
    void addAdmin(String firstName,String lastName , String adminId , String password);
    void deleteAdmin(Long adminId);
    List<AdminDto> showAdmins();
    void updateAdmin(Long id,String newFirstname , String newLastname , String adminId , String password );

}