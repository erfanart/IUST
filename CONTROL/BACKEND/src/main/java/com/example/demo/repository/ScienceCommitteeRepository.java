package com.example.demo.repository;

import com.example.demo.entity.Form;
import com.example.demo.entity.Laboratory;
import com.example.demo.entity.ScienceCommittee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScienceCommitteeRepository extends JpaRepository<ScienceCommittee, Long> {
    Optional<ScienceCommittee> findById(long id);

    @Query(value = "select * from science_committee where is_deleted=false", nativeQuery = true)
    List<ScienceCommittee> showScienceCommittees();
}
