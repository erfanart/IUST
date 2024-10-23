package IUST.EE.Control.Faculties.entity.dto;

import IUST.EE.Control.entity.BaseEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class FacultiesDto extends BaseEntity {
    private String firstName;
    private String lastName;
    private String mail;
    private String description;
    private FilesDto image;
}
