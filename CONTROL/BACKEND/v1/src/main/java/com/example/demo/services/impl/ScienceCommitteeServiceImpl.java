package com.example.demo.services.impl;

import com.example.demo.dto.ScienceCommitteeDto;
import com.example.demo.entity.ScienceCommittee;
import com.example.demo.exceptions.ScienceCommitteeException;
import com.example.demo.mapper.ScienceCommitteeMapper;
import com.example.demo.repository.ScienceCommitteeRepository;
import com.example.demo.services.ScienceCommitteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScienceCommitteeServiceImpl implements ScienceCommitteeService {
    private final ScienceCommitteeRepository scienceCommitteeRepository;

    @Autowired
    public ScienceCommitteeServiceImpl(ScienceCommitteeRepository scienceCommitteeRepository) {
        this.scienceCommitteeRepository = scienceCommitteeRepository;
    }

    @Override
    public ScienceCommittee save(ScienceCommittee scienceCommittee) {
        scienceCommitteeRepository.save(scienceCommittee);
        return scienceCommittee;
    }

    @Override
    public ScienceCommittee findById(Long id) {
        Optional<ScienceCommittee> scienceCommittee = scienceCommitteeRepository.findById(id);
        if (scienceCommittee.isPresent()){
            return scienceCommittee.get();
        }else {
            throw new ScienceCommitteeException("science committee not found");
        }
    }

    @Override
    public List<ScienceCommitteeDto> showScienceCommittees() {
        List<ScienceCommitteeDto> scienceCommitteeDtos = new ArrayList<>();
        for (ScienceCommittee sc: scienceCommitteeRepository.showScienceCommittees()
             ) {
            scienceCommitteeDtos.add(ScienceCommitteeMapper.scienceCommitteeToScienceCommitteeDto(sc));
        }
        return scienceCommitteeDtos;
    }
}
