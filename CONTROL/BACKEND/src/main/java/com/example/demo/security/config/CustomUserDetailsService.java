package com.example.demo.security.config;

import com.example.demo.entity.Admin;
import com.example.demo.entity.SuperAdmin;
import com.example.demo.exceptions.RoleException;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.SuperAdminRepository;
import com.example.demo.validation.Validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Validation.userType.equals("ADMIN")) {
            Optional<Admin> admin = adminRepository.findByAdminId(username);
            if (admin.isPresent()) {
                return new ManagerUserDetail(admin.get());
            }
            throw new RoleException("role not found");
        } else if (Validation.userType.equals("SUPER")) {
            Optional<SuperAdmin> superAdmin = superAdminRepository.findByUsername(username);
            if (superAdmin.isPresent()) {
                return new SuperAdminUserDetail(superAdmin.get());
            }
            throw new RoleException("role not found");
        } else {
            throw new RoleException("role not found");
        }
    }
}

