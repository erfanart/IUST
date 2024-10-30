package IUST.EE.Control.Faculties.mapper;

import IUST.EE.Control.Files.mapper.FilesMapper;
import IUST.EE.Control.Faculties.entity.FacultiesEntity;
import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;

public class FacultiesMapper {
    public static FacultiesEntity FacultiesDtoToFacultiesEntity(FacultiesDto facultiesDto) {
        return FacultiesEntity.builder()
                .id(facultiesDto.getId())
                .firstName(facultiesDto.getFirstName())
                .lastName(facultiesDto.getLastName())
                .mail(facultiesDto.getMail())
                .description(facultiesDto.getDescription())
                .image(FilesMapper.FilesDtoToFilesEntity(facultiesDto.getImage()))
                .build();
    }

    public static FacultiesDto FacultiesEntityToFacultiesDto(FacultiesEntity facultiesEntity) {
        return FacultiesDto.builder()
                .id(facultiesEntity.getId())
                .firstName(facultiesEntity.getFirstName())
                .lastName(facultiesEntity.getLastName())
                .mail(facultiesEntity.getMail())
                .description(facultiesEntity.getDescription())
                .image(FilesMapper.FilesEntityToFilesDto(facultiesEntity.getImage()))
                .build();
    }
}
