package IUST.EE.Control.Faculties.repository;

import IUST.EE.Control.Faculties.entity.FacultiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultiesRepository extends JpaRepository<FacultiesEntity, String> {
    @SuppressWarnings("null")
    Optional<FacultiesEntity> findById(String Id);

    @Query(value = "select * from faculties where is_deleted=false", nativeQuery = true)
    List<FacultiesEntity> showFaculties();
}
