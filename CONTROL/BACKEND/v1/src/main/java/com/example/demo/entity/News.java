package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class News extends BaseEntity<Long> {
    private String title;
    private String news;
    @Column(name = "news_number")
    private Timestamp newsNumber;
    private boolean isDeleted;
    @OneToOne
    private Image image;
}
