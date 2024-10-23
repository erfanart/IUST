package com.example.demo.mapper;

import com.example.demo.dto.FormDto;
import com.example.demo.entity.Form;

public class FormMapper {

    public static Form FormDtoToForm(FormDto formDto) {
        return Form.builder()
                .id(formDto.getId())
                .title(formDto.getTitle())
                .name(formDto.getName())
                .type(formDto.getType())
                .build();
    }

    public static FormDto FormToFormDto(Form form) {
        return FormDto.builder()
                .id(form.getId())
                .title(form.getTitle())
                .name(form.getName())
                .type(form.getType())
                .build();
    }
}
