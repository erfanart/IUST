package com.example.demo.services.impl;

import com.example.demo.dto.AdminDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.SuperAdmin;
import com.example.demo.exceptions.AdminException;
import com.example.demo.exceptions.PasswordException;
import com.example.demo.exceptions.RoleException;
import com.example.demo.repository.SuperAdminRepository;
import com.example.demo.security.config.AuthenticationResponse;
import com.example.demo.security.config.CustomUserDetailsService;
import com.example.demo.security.config.JwtService;
import com.example.demo.services.AdminService;
import com.example.demo.services.SuperAdminService;
import com.example.demo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final AdminService adminService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    private Validation validation = new Validation();

    @Autowired
    public SuperAdminServiceImpl(SuperAdminRepository superAdminRepository, AdminService adminService, JwtService jwtService, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.superAdminRepository = superAdminRepository;
        this.adminService = adminService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public AuthenticationResponse authenticate(String username, String password) {
        Optional<SuperAdmin> admin = superAdminRepository.findByUsername(username);
        if (admin.isPresent()) {
            if (password.equals(admin.get().getPassword()) || passwordEncoder.matches(password, admin.get().getPassword())) {

                String jwtToken = jwtService.generateToken(customUserDetailsService.loadUserByUsername(username));

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();

            } else {
                throw new PasswordException("password not match with manager id");
            }
        } else {
            throw new IllegalArgumentException("super admin not found");
        }

    }

    @Override
    public void changePassword(String password, String username) {
        Optional<SuperAdmin> superAdmin = superAdminRepository.findByUsername(username);
        if (superAdmin.isPresent()) {
            try {
                validation.validatePassword(password);
                superAdmin.get().setPassword(passwordEncoder.encode(password));
                superAdminRepository.save(superAdmin.get());
            } catch (PasswordException e) {
                throw new PasswordException(e.getMessage());
            }
        } else {
            throw new RoleException("super admin not found");
        }

    }

    @Override
    public void addAdmin(String firstName, String lastName, String adminId, String password) {
        try {
            validation.validatePassword(password);
            adminService.save(Admin.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .adminId(adminId)
                    .password(password)
                    .build());
        } catch (PasswordException e) {
            throw new PasswordException(e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Long adminID) {
        try {
            Admin admin = adminService.findByID(adminID);
            admin.setIsDeleted(true);
            adminService.save(admin);
        }catch (AdminException e){
            throw new AdminException(e.getMessage());
        }
    }

    @Override
    public List<AdminDto> showAdmins() {
        return adminService.showAdmins();
    }

    @Override
    public void updateAdmin(Long id,String newFirstname , String newLastname , String newAdminId , String newPassword ) {
        try {
            Admin admin = adminService.findByID(id);
            if (!Objects.equals(newFirstname, "")){
                admin.setFirstName(newFirstname);
            }
            if (!Objects.equals(newLastname, "")){
                admin.setLastName(newLastname);
            }
            if (!Objects.equals(newAdminId, "")){
                admin.setAdminId(newAdminId);
            }
            if (!Objects.equals(newPassword, "")){
                validation.validatePassword(newPassword);
                admin.setPassword(newPassword);
            }
            adminService.save(admin);
        }catch (AdminException e){
            throw new AdminException(e.getMessage());
        }
    }
}
