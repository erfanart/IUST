package com.example.demo.repository;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin>findByAdminId(String AdminId);

    @Query(value = "select * from Admin where is_deleted=false",nativeQuery = true)
    List<Admin> showAdmins();
}
