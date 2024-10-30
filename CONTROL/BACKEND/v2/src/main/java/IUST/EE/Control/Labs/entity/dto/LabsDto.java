package IUST.EE.Control.Labs.entity.dto;

import IUST.EE.Control.entity.BaseEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LabsDto extends BaseEntity {
    private String name;
    private String place;
    private String head;
    private String description;
    private boolean isDeleted;
    private FilesDto image;
}
