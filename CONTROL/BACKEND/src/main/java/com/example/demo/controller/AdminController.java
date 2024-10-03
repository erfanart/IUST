package com.example.demo.controller;

import com.example.demo.dto.FormDto;
import com.example.demo.dto.LaboratoryDto;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.security.config.AuthenticationResponse;
import com.example.demo.services.AdminService;
import com.example.demo.validation.Validation;
import org.springframework.http.MediaType;
import io.jsonwebtoken.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestParam @Validated String adminId,
            @RequestParam @Validated String password) {
        Validation.userType = "ADMIN";
        return ResponseEntity.ok(adminService.authenticate(adminId, password));
    }

    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestParam @Validated Long adminId,
            @RequestParam @Validated String password) {
        String message = "password changed";
        try {
            adminService.changePassword(password, adminId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add_form")
    public ResponseEntity<String> uploadImageToFileSystem(@RequestParam("pdf") MultipartFile file,
            @RequestParam @Validated String title) {
        String message = "form added";
        try {
            adminService.addForm(file, title);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add_laboratory")
    public ResponseEntity<String> addLaboratory(@RequestParam @Validated String name,
            @RequestParam @Validated String place,
            @RequestParam @Validated String head,
            @RequestParam @Validated String description) {
        String message = "laboratory added";
        try {
            adminService.addLaboratory(name, place, head, description);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add_news")
    public ResponseEntity<String> addNews(@RequestParam @Validated String title,
            @RequestParam @Validated String news,
            @RequestParam("image") MultipartFile file) {
        String message = "news added";
        try {
            adminService.addNews(title, news, file);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add_science_committee")
    public ResponseEntity<String> addScienceCommittee(@RequestParam @Validated String firstname,
            @RequestParam @Validated String lastname,
            @RequestParam @Validated String mail,
            @RequestParam @Validated String description,
            @RequestParam("image") MultipartFile file) {
        String message = "science committee added";
        try {
            adminService.addScienceCommittee(firstname, lastname, mail, description, file);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_form")
    public ResponseEntity<String> updateForm(@RequestParam @Validated Long formId,
            @RequestParam @Validated String newTitle,
            @RequestParam("image") MultipartFile newFile) {
        String message = "form updated";
        try {
            adminService.updateForm(formId, newTitle, newFile);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_laboratory")
    public ResponseEntity<String> updateLaboratory(@RequestParam @Validated Long laboratoryId,
            @RequestParam @Validated String newName,
            @RequestParam @Validated String newPlace,
            @RequestParam @Validated String newHead,
            @RequestParam @Validated String newDescription) {
        String message = "laboratory updated";
        try {
            adminService.updateLaboratory(laboratoryId, newName, newPlace, newHead, newDescription);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_news")
    public ResponseEntity<String> updateNews(@RequestParam @Validated Long newsId,
            @RequestParam @Validated String newTitle,
            @RequestParam @Validated String newNews,
            @RequestParam("image") MultipartFile newFile) {
        String message = "news updated";
        try {
            adminService.updateNews(newsId, newTitle, newNews, newFile);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_science_committee")
    public ResponseEntity<String> updateScienceCommittee(@RequestParam @Validated Long newScienceCommitteeId,
            @RequestParam @Validated String newFirstname,
            @RequestParam @Validated String newLastname,
            @RequestParam @Validated String newMail,
            @RequestParam @Validated String newDescription,
            @RequestParam("image") MultipartFile newFile) {
        String message = "science committee updated";
        try {
            adminService.updateScienceCommittee(newScienceCommitteeId, newFirstname, newLastname, newMail,
                    newDescription, newFile);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = adminService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    @GetMapping("/show_forms")
    public ResponseEntity<List<FormDto>> showForms() {
        return new ResponseEntity<>(adminService.showForms(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_laboratories")
    public ResponseEntity<List<LaboratoryDto>> showLaboratories() {
        return new ResponseEntity<>(adminService.showLaboratories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_news")
    public ResponseEntity<List<NewsDto>> showNews() {
        return new ResponseEntity<>(adminService.showNews(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_science_committees")
    public ResponseEntity<List<ScienceCommitteeDto>> showScienceCommittees() {
        return new ResponseEntity<>(adminService.showScienceCommittee(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_form")
    public ResponseEntity<String> deleteForm(Long formId) {
        String message = "form deleted";
        try {
            adminService.deleteForm(formId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_laboratory")
    public ResponseEntity<String> deleteLaboratory(Long laboratoryId) {
        String message = "laboratory deleted";
        try {
            adminService.deleteLaboratory(laboratoryId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_news")
    public ResponseEntity<String> deleteNews(Long newsId) {
        String message = "news deleted";
        try {
            adminService.deleteNews(newsId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_science_committee")
    public ResponseEntity<String> deleteScienceCommittee(Long scienceCommitteeId) {
        String message = "science committee deleted";
        try {
            adminService.deleteScienceCommittee(scienceCommitteeId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
