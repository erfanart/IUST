package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.Entity;
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
public class Form extends BaseEntity<Long> {
    private String title;
    private String name;
    private String type;
    private String filePath;
    private boolean isDeleted;
}