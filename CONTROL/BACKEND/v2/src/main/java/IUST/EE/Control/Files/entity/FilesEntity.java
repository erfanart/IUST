package IUST.EE.Control.Files.entity;

import IUST.EE.Control.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
@SuperBuilder
public class FilesEntity extends BaseEntity {
    private String type;
    private String filePath;
    private String relatedObject;
    private Boolean isDeleted;
}
