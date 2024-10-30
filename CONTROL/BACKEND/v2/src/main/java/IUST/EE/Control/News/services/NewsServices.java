package IUST.EE.Control.News.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import IUST.EE.Control.News.entity.NewsEntity;
import IUST.EE.Control.News.entity.dto.NewsDto;

public interface NewsServices {

    NewsEntity save(NewsEntity NewsEntity);

    NewsEntity findById(String id);

    List<NewsDto> showNews();

    String addNews(String title, String description, MultipartFile image);

    String editNews(String id, String title, String description, MultipartFile image);

    String deleteNews(String id);

    String disableNews(String id);

    String enableNews(String id);

}
