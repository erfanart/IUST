package com.example.demo.mapper;

import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.entity.ScienceCommittee;

public class ScienceCommitteeMapper {
    public static ScienceCommittee scienceCommitteeDtoToScienceCommittee(ScienceCommitteeDto scienceCommitteeDto){
        return ScienceCommittee.builder()
                .id(scienceCommitteeDto.getId())
                .firstName(scienceCommitteeDto.getFirstName())
                .lastName(scienceCommitteeDto.getLastName())
                .mail(scienceCommitteeDto.getMail())
                .description(scienceCommitteeDto.getDescription())
                .image(ImageMapper.imageDtoToImage(scienceCommitteeDto.getImageDto()))
                .build();
    }
    public static ScienceCommitteeDto scienceCommitteeToScienceCommitteeDto(ScienceCommittee scienceCommittee){
        return ScienceCommitteeDto.builder()
                .id(scienceCommittee.getId())
                .firstName(scienceCommittee.getFirstName())
                .lastName(scienceCommittee.getLastName())
                .mail(scienceCommittee.getMail())
                .description(scienceCommittee.getDescription())
                .imageDto(ImageMapper.imageToImageDto(scienceCommittee.getImage()))
                .build();
    }
}
