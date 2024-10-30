package IUST.EE.Control.News.entity;

import IUST.EE.Control.entity.BaseEntity;
import IUST.EE.Control.Files.entity.FilesEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity {
    private String title;
    private String news;
    @Column(name = "news_number")
    private Timestamp newsNumber;
    private Boolean isDeleted;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private FilesEntity image;
}
