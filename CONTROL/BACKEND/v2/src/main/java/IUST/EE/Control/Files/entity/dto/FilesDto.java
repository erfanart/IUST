package IUST.EE.Control.Files.entity.dto;

import IUST.EE.Control.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FilesDto extends BaseEntity {
    private String type;
    private String relatedObject;
}
