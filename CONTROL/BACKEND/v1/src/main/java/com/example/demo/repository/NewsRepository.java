package com.example.demo.repository;

import com.example.demo.entity.Form;
import com.example.demo.entity.Laboratory;
import com.example.demo.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    Optional<News> findById(long id);
    @Query(value = "select * from News where is_deleted=false",nativeQuery = true)
    List<News> showNews();
}
