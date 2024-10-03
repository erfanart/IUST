package com.example.demo.controller;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.FormDto;
import com.example.demo.exceptions.AdminException;
import com.example.demo.security.config.AuthenticationResponse;
import com.example.demo.services.SuperAdminService;
import com.example.demo.validation.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/super_admin")
public class SuperAdminController {
    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestParam @Validated String username,
                                                               @RequestParam @Validated String password) {
        Validation.userType = "SUPER";
        return ResponseEntity.ok(superAdminService.authenticate(username, password));
    }

    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestParam @Validated String adminId,
                                                 @RequestParam @Validated String password) {
        String message = "password changed";
        try {
            superAdminService.changePassword(password, adminId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add_admin")
    public ResponseEntity<String> deleteAdmin(@RequestParam @Validated String firstName,
                                              @RequestParam @Validated String lastName,
                                              @RequestParam @Validated String adminId,
                                              @RequestParam @Validated String password) {
        String message = "admin added";
        try {
            superAdminService.addAdmin(firstName, lastName, adminId, password);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new AdminException(e.getMessage());
        }
    }

    @DeleteMapping("/delete_admin")
    public ResponseEntity<String> addAdmin(@RequestParam @Validated Long adminId) {
        String message = "admin deleted";
        try {
            superAdminService.deleteAdmin(adminId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new AdminException(e.getMessage());
        }
    }
    @GetMapping("/show_admins")
    public ResponseEntity<List<AdminDto>> showForms() {
        return new ResponseEntity<>(superAdminService.showAdmins(), HttpStatus.ACCEPTED);
    }
    @PutMapping("/update_admin")
    public ResponseEntity<String> updateScienceCommittee(@RequestParam @Validated Long adminId,
                                                         @RequestParam @Validated String newFirstname,
                                                         @RequestParam @Validated String newLastname,
                                                         @RequestParam @Validated String newUsername,
                                                         @RequestParam @Validated String newPassword) {
        String message = "admin updated";
        try {
            superAdminService.updateAdmin(adminId,newFirstname,newLastname,newUsername,newPassword);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
