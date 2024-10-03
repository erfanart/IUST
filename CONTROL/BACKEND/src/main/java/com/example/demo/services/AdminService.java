package com.example.demo.services;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.FormDto;
import com.example.demo.dto.LaboratoryDto;
import com.example.demo.dto.NewsDto;
import com.example.demo.entity.Admin;
import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.security.config.AuthenticationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {

    Admin save(Admin admin);

    Admin findByID(Long id);

    List<AdminDto> showAdmins();

    AuthenticationResponse authenticate(String managerId, String password);

    void changePassword(String password, Long adminId);

    void addForm(MultipartFile file, String title);

    void addLaboratory(String name, String place, String head, String description);

    void addNews(String title, String news, MultipartFile file);

    void addScienceCommittee(String firstname, String lastName, String mail, String description, MultipartFile file);

    void updateForm(Long formId, String newTitle, MultipartFile file);

    void updateLaboratory(Long laboratoryId, String newName, String newPlace, String newHead, String newDescription);

    void updateNews(Long newsId, String newTitle, String newNews, MultipartFile file);

    void updateScienceCommittee(Long scienceCommitteeId, String newFirstname, String newLastname, String newMail, String newDescription, MultipartFile file);

    List<FormDto> showForms();

    List<LaboratoryDto> showLaboratories();

    List<NewsDto> showNews();

    List<ScienceCommitteeDto> showScienceCommittee();

    void deleteForm(Long formId);

    void deleteLaboratory(Long laboratoryId);

    void deleteNews(Long newsId);

    void deleteScienceCommittee(Long ScienceCommitteeId);
    
    byte[] downloadImage(String name);
}
