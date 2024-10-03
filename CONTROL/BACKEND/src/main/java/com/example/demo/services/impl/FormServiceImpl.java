package com.example.demo.services.impl;

import com.example.demo.dto.FormDto;
import com.example.demo.entity.Form;
import com.example.demo.exceptions.FormException;
import com.example.demo.mapper.FormMapper;
import com.example.demo.repository.FormRepository;
import com.example.demo.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;

    @Autowired
    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }
    @Override
    public Form save(Form form) {
        formRepository.save(form);
        return form;
    }

    @Override
    public Form findById(Long id) {
        Optional<Form> form = formRepository.findById(id);
        if (form.isPresent()){
            return form.get();
        }else {
            throw new FormException("form not found");
        }
    }

    @Override
    public List<FormDto> showForms() {
        List<FormDto> formDtos = new ArrayList<>();
        for (Form f:formRepository.showForms()
             ) {
            formDtos.add(FormMapper.FormToFormDto(f));
        }
        return formDtos;
    }

}
