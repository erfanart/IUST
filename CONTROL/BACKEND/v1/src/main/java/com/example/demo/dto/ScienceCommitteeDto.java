package com.example.demo.dto;

import com.example.demo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class ScienceCommitteeDto extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private String mail;
    private String description;
    private ImageDto imageDto;
}
