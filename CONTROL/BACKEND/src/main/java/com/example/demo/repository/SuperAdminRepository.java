package com.example.demo.repository;

import com.example.demo.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long>  {
Optional<SuperAdmin>  findByUsername(String userName);
}
