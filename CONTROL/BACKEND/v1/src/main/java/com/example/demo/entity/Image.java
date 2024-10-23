package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Image extends BaseEntity<Long> {
    private String name;
    private String type;
    private String filePath;
    private Boolean isDeleted;
}
