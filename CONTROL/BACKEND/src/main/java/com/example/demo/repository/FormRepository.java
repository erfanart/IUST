package com.example.demo.repository;

import com.example.demo.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form,Long> {
    @Query(value = "select * from Form where is_deleted=false",nativeQuery = true)
    List<Form> showForms();
}
