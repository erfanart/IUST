package com.example.demo.dto;

import com.example.demo.base.BaseEntity;
import com.example.demo.entity.Image;
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
@SuperBuilder
public class NewsDto extends BaseEntity<Long> {
    private String title;
    private String news;
    private Timestamp newsNumber;
    private ImageDto imageDto;
}
