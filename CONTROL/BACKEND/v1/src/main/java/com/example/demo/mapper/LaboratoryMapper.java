package com.example.demo.mapper;

import com.example.demo.dto.LaboratoryDto;
import com.example.demo.entity.Laboratory;

public class LaboratoryMapper {
    public static Laboratory laboratoryDtoToLaboratory(LaboratoryDto laboratoryDto){
        return Laboratory.builder()
                .id(laboratoryDto.getId())
                .name(laboratoryDto.getName())
                .place(laboratoryDto.getPlace())
                .head(laboratoryDto.getHead())
                .description(laboratoryDto.getDescription())
                .build();
    }
    public static LaboratoryDto laboratoryToLaboratoryDto(Laboratory laboratory){
        return LaboratoryDto.builder()
                .id(laboratory.getId())
                .name(laboratory.getName())
                .place(laboratory.getPlace())
                .head(laboratory.getHead())
                .description(laboratory.getDescription())
                .build();
    }
}
