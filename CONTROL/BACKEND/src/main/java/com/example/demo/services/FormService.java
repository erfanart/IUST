package com.example.demo.services;

import com.example.demo.dto.FormDto;
import com.example.demo.entity.Form;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FormService {
    Form save(Form form);
    Form findById(Long id);
    List<FormDto> showForms();

}
