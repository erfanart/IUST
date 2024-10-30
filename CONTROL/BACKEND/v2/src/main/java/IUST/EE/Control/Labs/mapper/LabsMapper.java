package IUST.EE.Control.Labs.mapper;

import IUST.EE.Control.Files.mapper.FilesMapper;
import IUST.EE.Control.Labs.entity.LabsEntity;
import IUST.EE.Control.Labs.entity.dto.LabsDto;


public class LabsMapper {

    public static LabsEntity labsDtoToLabsEntity(LabsDto labsDto){
        return LabsEntity.builder()
                .id(labsDto.getId())
                .name(labsDto.getName())
                .place(labsDto.getPlace())
                .description(labsDto.getDescription())
                .image(FilesMapper.FilesDtoToFilesEntity(labsDto.getImage()))
                .build();
    }
    public static LabsDto labsEntityToLabsDto(LabsEntity labsEntity){
        return LabsDto.builder()
                .id(labsEntity.getId())
                .name(labsEntity.getName())
                .place(labsEntity.getPlace())
                .description(labsEntity.getDescription())
                .image(FilesMapper.FilesEntityToFilesDto(labsEntity.getImage()))
                .build();
    }

}
