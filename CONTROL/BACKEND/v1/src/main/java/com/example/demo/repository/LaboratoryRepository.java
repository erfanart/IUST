package com.example.demo.repository;

import com.example.demo.entity.Form;
import com.example.demo.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory,Long> {
    Optional<Laboratory> findById(long id);
    @Query(value = "select * from Laboratory where is_deleted=false",nativeQuery = true)
    List<Laboratory> showLaboratories();
}
