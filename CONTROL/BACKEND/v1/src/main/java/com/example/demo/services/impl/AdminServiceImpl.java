package com.example.demo.services.impl;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.FormDto;
import com.example.demo.dto.LaboratoryDto;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.entity.*;
import com.example.demo.entity.enums.AdminRoles;
import com.example.demo.exceptions.*;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.FormMapper;
import com.example.demo.mapper.LaboratoryMapper;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.mapper.ScienceCommitteeMapper;
import com.example.demo.repository.AdminRepository;
import com.example.demo.security.config.AuthenticationResponse;
import com.example.demo.security.config.CustomUserDetailsService;
import com.example.demo.security.config.JwtService;
import com.example.demo.services.*;
import com.example.demo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final FormService formService;
    private final LaboratoryService laboratoryService;
    private final NewsService newsService;
    private final ScienceCommitteeService scienceCommitteeService;
    private final ImageService imageService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private Validation validation = new Validation();

    @Value("${spring.path.formsFolderPath}")
    private String formsFolderPAth;
    @Value("${spring.path.newsFolderPath}")
    private String newsFolderPath;
    @Value("${spring.path.scienceCommitteeFolderPath}")
    private String scienceCommitteeFolderPath;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, FormService formService,
            LaboratoryService laboratoryService, NewsService newsService,
            ScienceCommitteeService scienceCommitteeService, ImageService imageService, JwtService jwtService,
            PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.adminRepository = adminRepository;
        this.formService = formService;
        this.laboratoryService = laboratoryService;
        this.newsService = newsService;
        this.scienceCommitteeService = scienceCommitteeService;
        this.imageService = imageService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void addAdmin(String firstname, String lastname, String adminId, String password, String adminRole) {
        try {
            validation.validatePassword(password);
            Admin admin = Admin.builder()
                    .firstName(firstname)
                    .lastName(lastname)
                    .adminId(adminId)
                    .password(password)
                    .isDeleted(false)
                    .build();
            if (adminRole.equals("ADMIN")) {
                admin.setAdminRoles(AdminRoles.ADMIN);
            } else if (adminRole.equals("SUPER")) {
                admin.setAdminRoles(AdminRoles.SUPER);
            } else {
                throw new AdminException("please enter ADMIN or SUPER for admin role");
            }
            adminRepository.save(admin);
        } catch (Exception e) {
            throw new AdminException(e.getMessage());
        }
    }

    @Override
    public void updateAdmin(Long id, String firstname, String lastname, String adminId, String password,
            String adminRole) {
        try {
            Optional<Admin> admin = adminRepository.findById(id);
            if (admin.isPresent()) {
                if (!Objects.equals(adminRole, "")) {
                    if (adminRole.equals("ADMIN")) {
                        admin.get().setAdminRoles(AdminRoles.ADMIN);
                        admin.get().setAdminRoles(AdminRoles.ADMIN);
                    } else if (adminRole.equals("SUPER")) {
                        admin.get().setAdminRoles(AdminRoles.SUPER);
                        admin.get().setAdminRoles(AdminRoles.SUPER);
                    } else {
                        throw new AdminException("please enter ADMIN or SUPER for admin role");
                    }
                }
                if (!Objects.equals(password, "")) {
                    validation.validatePassword(password);
                    admin.get().setPassword(password);
                }
                if (!Objects.equals(adminId, "")) {
                    admin.get().setAdminId(adminId);
                }
                if (!Objects.equals(lastname, "")) {
                    admin.get().setLastName(lastname);
                }
                if (!Objects.equals(firstname, "")) {
                    admin.get().setFirstName(firstname);
                }
                adminRepository.save(admin.get());
            } else {
                throw new AdminException("admin not found");
            }
        } catch (Exception e) {
            throw new AdminException(e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            admin.get().setIsDeleted(true);
            adminRepository.save(admin.get());
        } else {
            throw new AdminException("admin not found");
        }
    }

    @Override
    public Optional<Admin> findByID(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<AdminDto> showAdmins() {
        List<AdminDto> adminDtos = new ArrayList<>();
        for (Admin a : adminRepository.showAdmins()) {
            adminDtos.add(AdminMapper.adminToAdminDto(a));
        }
        return adminDtos;
    }

    @Override
    public AuthenticationResponse authenticate(String managerId, String password) {
        Optional<Admin> admin = adminRepository.findByAdminId(managerId);
        if (admin.isPresent()) {
            if (admin.get().getAdminRoles().equals(AdminRoles.ADMIN)) {
                Validation.userType = "ADMIN";
            } else {
                Validation.userType = "SUPER";
            }
            if (password.equals(admin.get().getPassword())
                    || passwordEncoder.matches(password, admin.get().getPassword())) {

                String jwtToken = jwtService.generateToken(customUserDetailsService.loadUserByUsername(managerId));

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            } else {
                throw new PasswordException("password not match with manager id");
            }
        } else {
            throw new IllegalArgumentException("admin not found");
        }
    }

    @Override
    @Transactional
    public void changePassword(String password, Long adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isPresent()) {
            try {
                validation.validatePassword(password);
                admin.get().setPassword(passwordEncoder.encode(password));
                adminRepository.save(admin.get());
            } catch (PasswordException e) {
                throw new PasswordException(e.getMessage());
            }
        } else {
            throw new RoleException("admin not found");
        }

    }

    @Override
    public void addForm(MultipartFile file, String title) {
        try {
            String filePath = formsFolderPAth + file.getOriginalFilename();
            String type = file.getContentType();
            if (!type.equals("application/pdf")) {
                throw new FormException("please upload file with pdf format");
            }
            formService.save(Form.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .filePath(filePath)
                    .isDeleted(false)
                    .title(title)
                    .build());
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            throw new FormException(e.getMessage());
        }
    }

    @Override
    public void addLaboratory(String name, String place, String head, String description) {
        Laboratory laboratory = new Laboratory(name, place, head, description, false);
        laboratoryService.save(laboratory);
    }

    @Override
    public void addNews(String title, String news, MultipartFile file) {
        try {
            String filePath = newsFolderPath + file.getOriginalFilename();
            validation.validationImage(file.getContentType());
            newsService.save(News.builder()
                    .title(title)
                    .news(news)
                    .isDeleted(false)
                    .newsNumber(Timestamp.from(Instant.now()))
                    .image(imageService.save(Image.builder().name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .filePath(filePath)
                            .isDeleted(false)
                            .build()))
                    .build());
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            throw new ImageException(e.getMessage());
        }
    }

    @Override
    public void addScienceCommittee(String firstname, String lastName, String mail, String description,
            MultipartFile file) {
        try {
            validation.validateMail(mail);
            validation.validationImage(file.getContentType());
            String filePath = scienceCommitteeFolderPath + file.getOriginalFilename();
            scienceCommitteeService.save(ScienceCommittee.builder().firstName(firstname)
                    .lastName(lastName)
                    .mail(mail)
                    .description(description)
                    .isDeleted(false)
                    .image(imageService.save(Image.builder().name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .filePath(filePath)
                            .isDeleted(false)
                            .build()))
                    .build());
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            throw new ScienceCommitteeException(e.getMessage());
        }
    }

    @Override
    public void updateForm(Long formId, String newTitle, MultipartFile file) {
        try {
            String filePath = formsFolderPAth + file.getOriginalFilename();
            Form form = formService.findById(formId);
            if (!Objects.equals(newTitle, "")) {
                form.setTitle(newTitle);
            }
            if (!file.isEmpty()) {
                if (!file.getContentType().equals("application/pdf")) {
                    throw new FormException("please upload file with pdf format");
                }
                Path oldFile = Path.of(form.getFilePath());
                Files.deleteIfExists(oldFile);
                file.transferTo(new File(filePath));

                form.setName(file.getOriginalFilename());
                form.setType(file.getContentType());
                form.setFilePath(filePath);

            }
            formService.save(form);
        } catch (Exception e) {
            throw new FormException(e.getMessage());
        }
    }

    @Override
    public void updateLaboratory(Long laboratoryId, String newName, String newPlace, String newHead,
            String newDescription) {
        try {
            Laboratory laboratory = laboratoryService.findById(laboratoryId);
            if (!Objects.equals(newName, "")) {
                laboratory.setName(newName);
            }
            if (!Objects.equals(newPlace, "")) {
                laboratory.setPlace(newPlace);
            }
            if (!Objects.equals(newHead, "")) {
                laboratory.setHead(newHead);
            }
            if (!Objects.equals(newDescription, "")) {
                laboratory.setDescription(newDescription);
            }
            laboratoryService.save(laboratory);
        } catch (LaboratoryException e) {
            throw new LaboratoryException(e.getMessage());
        }
    }

    @Override
    public void updateNews(Long newsId, String newTitle, String newNews, MultipartFile file) {
        try {
            News news = newsService.findById(newsId);
            Image image = imageService.findById(news.getImage().getId());
            if (!file.isEmpty()) {
                validation.validationImage(file.getContentType());
                Path oldFile = Path.of(news.getImage().getFilePath());
                Files.deleteIfExists(oldFile);
                String filePath = newsFolderPath + file.getOriginalFilename();
                file.transferTo(new File(filePath));
                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());
                image.setFilePath(filePath);
                imageService.save(image);
                news.setImage(image);
            }
            try {
                if (!Objects.equals(newTitle, "")) {
                    news.setTitle(newTitle);
                }
                if (!Objects.equals(newNews, "")) {
                    news.setNews(newNews);
                }
                newsService.save(news);
            } catch (NewsException e) {
                throw new NewsException(e.getMessage());
            }
        } catch (Exception e) {
            throw new ImageException(e.getMessage());
        }
    }

    @Override
    public void updateScienceCommittee(Long scienceCommitteeId, String newFirstname, String newLastname, String newMail,
            String newDescription, MultipartFile file) {

        try {
            ScienceCommittee scienceCommittee = scienceCommitteeService.findById(scienceCommitteeId);
            try {
                if (!Objects.equals(newMail, "")) {
                    validation.validateMail(newMail);
                    scienceCommittee.setMail(newMail);
                }
                try {
                    if (!file.isEmpty()) {
                        validation.validationImage(file.getContentType());
                        String filePath = scienceCommitteeFolderPath + file.getOriginalFilename();
                        Image image = imageService.findById(scienceCommittee.getImage().getId());
                        Path oldFile = Path.of(scienceCommittee.getImage().getFilePath());
                        Files.deleteIfExists(oldFile);
                        file.transferTo(new File(filePath));
                        scienceCommittee.setImage(image);
                    }
                    if (!Objects.equals(newFirstname, "")) {
                        scienceCommittee.setFirstName(newFirstname);
                    }
                    if (!Objects.equals(newLastname, "")) {
                        scienceCommittee.setLastName(newLastname);
                    }
                    if (!Objects.equals(newDescription, "")) {
                        scienceCommittee.setDescription(newDescription);
                    }
                    scienceCommitteeService.save(scienceCommittee);
                } catch (Exception e) {
                    throw new ImageException(e.getMessage());
                }
            } catch (MailException e) {
                throw new MailException(e.getMessage());
            }
        } catch (ScienceCommitteeException e) {
            throw new ScienceCommitteeException(e.getMessage());
        }
    }

    @Override
    public List<FormDto> showForms() {
        return formService.showForms();
    }

    @Override
    public List<LaboratoryDto> showLaboratories() {
        return laboratoryService.showLaboratories();
    }

    @Override
    public List<NewsDto> showNews() {
        return newsService.showNews();
    }

    @Override
    public List<ScienceCommitteeDto> showScienceCommittee() {
        return scienceCommitteeService.showScienceCommittees();
    }

    @Override
    public void deleteForm(Long formId) {
        try {
            Form form = formService.findById(formId);
            form.setDeleted(true);
            formService.save(form);
        } catch (FormException e) {
            throw new FormException(e.getMessage());
        }
    }

    @Override
    public void deleteLaboratory(Long laboratoryId) {
        try {
            Laboratory laboratory = laboratoryService.findById(laboratoryId);
            laboratory.setDeleted(true);
            laboratoryService.save(laboratory);
        } catch (LaboratoryException e) {
            throw new LaboratoryException(e.getMessage());
        }
    }

    @Override
    public void deleteNews(Long newsId) {
        try {
            News news = newsService.findById(newsId);
            try {
                Image image = imageService.findById(news.getImage().getId());
                image.setIsDeleted(true);
                news.setDeleted(true);
                imageService.save(image);
                newsService.save(news);
            } catch (ImageException e) {
                throw new ImageException(e.getMessage());
            }
        } catch (NewsException e) {
            throw new NewsException(e.getMessage());
        }
    }

    @Override
    public void deleteScienceCommittee(Long ScienceCommitteeId) {
        try {
            ScienceCommittee scienceCommittee = scienceCommitteeService.findById(ScienceCommitteeId);
            try {
                Image image = imageService.findById(scienceCommittee.getImage().getId());
                scienceCommittee.setDeleted(true);
                image.setIsDeleted(true);
                imageService.save(image);
                scienceCommitteeService.save(scienceCommittee);
            } catch (ImageException e) {
                throw new ImageException(e.getMessage());
            }
        } catch (ScienceCommitteeException e) {
            throw new ScienceCommitteeException(e.getMessage());
        }
    }

    @Override
    public byte[] downloadImage(String name) {
        try {
            return imageService.downloadImage(name);
        } catch (ImageException e) {
            throw new ImageException(e.getMessage());
        }
    }
}
