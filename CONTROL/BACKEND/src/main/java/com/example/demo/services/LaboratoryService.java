package com.example.demo.services;

import com.example.demo.dto.LaboratoryDto;
import com.example.demo.entity.Laboratory;

import java.util.List;

public interface LaboratoryService {
    Laboratory save(Laboratory laboratory);
    Laboratory findById(Long Id);
    List<LaboratoryDto> showLaboratories();

}
