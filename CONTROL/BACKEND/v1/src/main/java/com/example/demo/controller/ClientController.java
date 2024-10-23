package com.example.demo.controller;

import com.example.demo.dto.FormDto;
import com.example.demo.dto.LaboratoryDto;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ClientController {
    private final FormService formService;
    private final LaboratoryService laboratoryService;
    private final AdminService adminService;
    private final NewsService newsService;
    private final ScienceCommitteeService scienceCommitteeService;

    @Autowired
    public ClientController(FormService formService, LaboratoryService laboratoryService, AdminService adminService,
            NewsService newsService, ScienceCommitteeService scienceCommitteeService) {
        this.formService = formService;
        this.laboratoryService = laboratoryService;
        this.adminService = adminService;
        this.newsService = newsService;
        this.scienceCommitteeService = scienceCommitteeService;
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = adminService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/show_forms")
    public ResponseEntity<List<FormDto>> showForms() {
        return new ResponseEntity<>(formService.showForms(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_laboratories")
    public ResponseEntity<List<LaboratoryDto>> showLaboratories() {
        return new ResponseEntity<>(laboratoryService.showLaboratories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_news")
    public ResponseEntity<List<NewsDto>> showNews() {
        return new ResponseEntity<>(newsService.showNews(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/show_science_committees")
    public ResponseEntity<List<ScienceCommitteeDto>> showScienceCommittees() {
        return new ResponseEntity<>(scienceCommitteeService.showScienceCommittees(), HttpStatus.ACCEPTED);
    }
}
