package IUST.EE.Control.Labs.entity;

import IUST.EE.Control.entity.BaseEntity;
import IUST.EE.Control.Files.entity.FilesEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "labs")
public class LabsEntity extends BaseEntity {
    private String name;
    private String place;
    private String head;
    private String description;
    private Boolean isDeleted;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private FilesEntity image;
}
