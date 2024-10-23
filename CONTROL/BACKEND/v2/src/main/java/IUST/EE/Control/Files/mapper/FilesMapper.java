package IUST.EE.Control.Files.mapper;

import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;

public class FilesMapper {
    public static FilesEntity FilesDtoToFilesEntity(FilesDto FilesDto) {
        return FilesEntity.builder()
                .id(FilesDto.getId())
                .relatedObject(FilesDto.getRelatedObject())
                .type(FilesDto.getType())
                .build();
    }

    public static FilesDto FilesEntityToFilesDto(FilesEntity FilesEntity) {
        FilesDto FilesDtos = FilesDto.builder()
                .id(FilesEntity.getId())
                .relatedObject(FilesEntity.getRelatedObject())
                .type(FilesEntity.getType())
                .build();
        return FilesDtos;
    }
}
