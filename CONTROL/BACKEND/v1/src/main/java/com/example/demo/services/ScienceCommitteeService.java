package com.example.demo.services;

import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.entity.ScienceCommittee;

import java.util.List;

public interface ScienceCommitteeService {
    ScienceCommittee save(ScienceCommittee scienceCommittee);
    ScienceCommittee findById(Long id);
    List<ScienceCommitteeDto> showScienceCommittees();

}
