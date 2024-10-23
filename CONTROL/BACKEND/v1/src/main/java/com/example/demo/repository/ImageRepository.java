package com.example.demo.repository;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
Optional<Image> findById(Long id);

@Query(value = "select * from image where is_deleted = false and name=:name", nativeQuery = true)
Optional<Image> findByName(@Param("name") String name);
}
