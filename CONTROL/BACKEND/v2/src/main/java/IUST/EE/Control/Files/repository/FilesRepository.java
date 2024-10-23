package IUST.EE.Control.Files.repository;

import IUST.EE.Control.Files.entity.FilesEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, String> {
    @SuppressWarnings("null")
    Optional<FilesEntity> findById(String id);

    @Query(value = "select * from files where is_deleted=false and type= ?2 and related_object= ?1 and id= ?3", nativeQuery = true)
    Optional<FilesEntity> findFile(String relatedObject, String type, String id);

    @Modifying
    @Query(value = "select * from files where is_deleted=false and type= ?1 and related_object=?2", nativeQuery = true)
    List<FilesEntity> showFiles(String type, String relatedObject);
}
