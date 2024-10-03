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
public class Laboratory extends BaseEntity<Long> {
    private String name;
    private String place;
    private String head;
    private String description;
    private boolean isDeleted;
}
