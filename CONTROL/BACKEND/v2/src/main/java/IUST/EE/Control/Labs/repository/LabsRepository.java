package IUST.EE.Control.Labs.repository;

import IUST.EE.Control.Labs.entity.LabsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabsRepository extends JpaRepository<LabsEntity, String> {
    @SuppressWarnings("null")
    Optional<LabsEntity> findById(String id);

    @Query(value = "select * from labs where is_deleted=false", nativeQuery = true)
    List<LabsEntity> showLabs();
}
