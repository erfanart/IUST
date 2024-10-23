package com.example.demo.services.impl;

import com.example.demo.dto.LaboratoryDto;
import com.example.demo.entity.Laboratory;
import com.example.demo.exceptions.LaboratoryException;
import com.example.demo.mapper.LaboratoryMapper;
import com.example.demo.repository.LaboratoryRepository;
import com.example.demo.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public LaboratoryServiceImpl(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Laboratory save(Laboratory laboratory) {
        laboratoryRepository.save(laboratory);
        return laboratory;
    }

    @Override
    public Laboratory findById(Long id) {
        Optional<Laboratory> laboratory = laboratoryRepository.findById(id);
        if (laboratory.isPresent()){
            return laboratory.get();
        }else {
            throw new LaboratoryException("laboratory not found");
        }
    }

    @Override
    public List<LaboratoryDto> showLaboratories() {
        List<LaboratoryDto> laboratoryDtos = new ArrayList<>();
        for (Laboratory l: laboratoryRepository.showLaboratories()
             ) {
            laboratoryDtos.add(LaboratoryMapper.laboratoryToLaboratoryDto(l));
        }
        return laboratoryDtos;
    }
}
