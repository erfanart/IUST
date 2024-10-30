package IUST.EE.Control.News.repository;

import IUST.EE.Control.News.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String> {
    @SuppressWarnings("null")
    Optional<NewsEntity> findById(String Id);

    @Query(value = "select * from news where is_deleted=false", nativeQuery = true)
    List<NewsEntity> showNews();

}