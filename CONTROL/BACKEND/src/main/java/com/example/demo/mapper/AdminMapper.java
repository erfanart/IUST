package com.example.demo.mapper;

import com.example.demo.dto.AdminDto;
import com.example.demo.entity.Admin;

public class AdminMapper {
    public static Admin adminDtoToAdmin(AdminDto adminDto){
        return Admin.builder()
                .id(adminDto.getId())
                .adminId(adminDto.getAdminId())
                .password(adminDto.getPassword())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .build();
    }
    public static AdminDto adminToAdminDto(Admin admin){
        return AdminDto.builder()
                .id(admin.getId())
                .adminId(admin.getAdminId())
                .password(admin.getPassword())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .build();
    }
}