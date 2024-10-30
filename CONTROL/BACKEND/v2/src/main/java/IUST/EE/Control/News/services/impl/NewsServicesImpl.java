package IUST.EE.Control.News.services.impl;
import java.sql.Timestamp;
import java.time.Instant;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import IUST.EE.Control.Properties;
import IUST.EE.Control.News.entity.NewsEntity;
import IUST.EE.Control.News.entity.dto.NewsDto;
import IUST.EE.Control.News.exceptions.NewsException;
import IUST.EE.Control.News.mapper.NewsMapper;
import IUST.EE.Control.News.repository.NewsRepository;
import IUST.EE.Control.News.services.NewsServices;
import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.exceptions.FilesExceptions;
import IUST.EE.Control.Files.repository.FilesRepository;
import IUST.EE.Control.Files.services.FilesServices;


@Service
public class NewsServicesImpl implements NewsServices {

    private final NewsRepository newsRepository;
    private final FilesRepository filesRepository;
    private final FilesServices filesServices;
    private final Properties properties;

    @Autowired
    public NewsServicesImpl(NewsRepository newsRepository,
            FilesRepository filesRepository,
            FilesServices filesServices,
            Properties properties) {

        this.newsRepository = newsRepository;
        this.filesRepository = filesRepository;
        this.filesServices = filesServices;
        this.properties = properties;
    }

    @Override
    public NewsEntity save(NewsEntity newsEntity) {
        newsRepository.save(newsEntity);
        return newsEntity;
    }

    @Override
    public NewsEntity findById(String id) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(id);
        if (newsEntity.isPresent()) {
            return newsEntity.get();
        } else {
            throw new NewsException("news not found");
        }
    }

    @Override
    public List<NewsDto> showNews() {
        List<NewsDto> ans = new ArrayList<>();
        for (NewsEntity n : newsRepository.showNews()) {
            ans.add(NewsMapper.newsEntityToNewsDto(n));
        }
        return ans;
    }

    private FilesEntity addImage(MultipartFile image, String uuid) {
        @SuppressWarnings("unchecked")
        Map<String, Object> PWD = (Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) properties.DataStore
                .get("public"))
                .get("news"))
                .get("images");
        String WD = (String) PWD.get("dir");
        File directory = new File(WD);
        if (!directory.exists()) {
            System.out.println(WD + " Not Found lets make it ...");
            directory.mkdirs();
        }
        String imageType = (String) image.getContentType();
        String extension = "";
        if (imageType != null) {
            switch (imageType) {
                case "image/jpeg":
                    extension = ".jpg";
                    break;
                case "image/png":
                    extension = ".png";
                    break;
                default:
                    extension = ".jpg";
            }
        }
        ;

        String imagePath = WD + "/" + uuid + extension;
        File destinationFile = new File(imagePath);
        try {
            System.out.println(" Uploaded file path is: " + destinationFile.getAbsolutePath());
            Files.copy(image.getInputStream(), destinationFile.toPath());
        } catch (IOException e) {
            System.err.println("Error transferring the file: " + e.getMessage());
            e.printStackTrace();
        }
        FilesEntity imageEntity = new FilesEntity();
        imageEntity.setFilePath(destinationFile.toPath().toString());
        imageEntity.setRelatedObject("news");
        imageEntity.setType(imageType);
        imageEntity.setIsDeleted(false);
        filesRepository.save(imageEntity);
        return imageEntity;
    }

    @Override
    public String addNews(String title, String news, MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String imageName = uuid.toString();
        NewsEntity newsEntity = new NewsEntity();
        Instant instant = Instant.now();
        newsEntity.setTitle(title);
        newsEntity.setNews(news);
        newsEntity.setIsDeleted(false);
        newsEntity.setNewsNumber(Timestamp.from(instant));
        newsEntity.setImage(addImage(image,imageName));
        this.save(newsEntity);
        return "news added";

    }

    private FilesEntity deleteImage(FilesEntity image) {
        Optional<FilesEntity> imageRepo = filesRepository.findById(image.getId());
        if (imageRepo.isPresent()) {
            FilesEntity imageEntity = imageRepo.get();
            File destinationFile = new File(imageEntity.getFilePath());
            try {
                System.out.println("  file path is: " + destinationFile.getAbsolutePath() + " will be delete");
                Files.deleteIfExists(destinationFile.toPath());
            } catch (IOException e) {
                System.err.println("Error transferring the file: " + e.getMessage());
                e.printStackTrace();
            }
            return image;
        } else {
            throw new FilesExceptions();
        }
    }

    @Override
    public String editNews(String id,
            String title,
            String news,
            MultipartFile image) {

        NewsEntity newsEntity = this.findById(id);
        if (title != null) {
            newsEntity.setTitle(title);
        }
        if (news != null) {
            newsEntity.setNews(news);
        }
        if (image != null) {
            String uuid = UUID.randomUUID().toString();
            FilesEntity lastImage = newsEntity.getImage();
            newsEntity.setImage(addImage(image, uuid));
            filesServices.deleteFile(this.deleteImage(lastImage).getId(), "news");
        }
        this.save(newsEntity);
        return id + " Edited successfully";

    }

    @Override
    public String deleteNews(String id) {
        NewsEntity newsEntity = this.findById(id);
        newsRepository.delete(newsEntity);
        return id + " Deleted successfully";

    }

    @Override
    public String disableNews(String id) {
        NewsEntity newsEntity = this.findById(id);
        newsEntity.setIsDeleted(true);
        this.save(newsEntity);
        return id + " Disabled successfully";
    }

    @Override
    public String enableNews(String id) {
        NewsEntity newsEntity = this.findById(id);
        newsEntity.setIsDeleted(false);
        this.save(newsEntity);
        return id + " Disabled successfully";
    }

}
