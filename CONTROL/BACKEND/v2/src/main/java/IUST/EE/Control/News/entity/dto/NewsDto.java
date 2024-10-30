package IUST.EE.Control.News.entity.dto;

import IUST.EE.Control.entity.BaseEntity;

import IUST.EE.Control.Files.entity.dto.FilesDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NewsDto extends BaseEntity {
    private String title;
    private String news;
    private Timestamp newsNumber;
    private FilesDto image;
}