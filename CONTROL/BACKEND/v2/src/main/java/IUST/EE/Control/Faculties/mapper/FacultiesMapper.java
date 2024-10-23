package IUST.EE.Control.Faculties.mapper;

import IUST.EE.Control.Files.mapper.FilesMapper;
import IUST.EE.Control.Faculties.entity.FacultiesEntity;
import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;

public class FacultiesMapper {
    public static FacultiesEntity FacultiesDtoToFacultiesEntity(FacultiesDto FacultiesDto) {
        return FacultiesEntity.builder()
                .id(FacultiesDto.getId())
                .firstName(FacultiesDto.getFirstName())
                .lastName(FacultiesDto.getLastName())
                .mail(FacultiesDto.getMail())
                .description(FacultiesDto.getDescription())
                .image(FilesMapper.FilesDtoToFilesEntity(FacultiesDto.getImage()))
                .build();
    }

    public static FacultiesDto FacultiesEntityToFacultiesDto(FacultiesEntity FacultiesEntity) {
        return FacultiesDto.builder()
                .id(FacultiesEntity.getId())
                .firstName(FacultiesEntity.getFirstName())
                .lastName(FacultiesEntity.getLastName())
                .mail(FacultiesEntity.getMail())
                .description(FacultiesEntity.getDescription())
                .image(FilesMapper.FilesEntityToFilesDto(FacultiesEntity.getImage()))
                .build();
    }
}
