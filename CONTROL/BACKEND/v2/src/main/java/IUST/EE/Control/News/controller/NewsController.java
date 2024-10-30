package IUST.EE.Control.News.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.services.FilesServices;
import IUST.EE.Control.News.entity.dto.NewsDto;
import IUST.EE.Control.News.services.NewsServices;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsServices newsServices;
    private final FilesServices filesServices;

    @Autowired
    public NewsController(NewsServices newsServices, FilesServices filesServices) {
        this.newsServices = newsServices;
        this.filesServices = filesServices;
    };

    @GetMapping("/")
    public String index() {
        return "News Page";
    }

    @GetMapping("/show")
    public ResponseEntity<List<NewsDto>> showNews() {
        return new ResponseEntity<>(newsServices.showNews(), HttpStatus.ACCEPTED);
    };

    @GetMapping("/image/{name}")
    public ResponseEntity<?> downloadImage(String relatedObject, String type, @PathVariable String name)
            throws IOException {
        byte[] imageData = filesServices.download(relatedObject = "news", name);
        FilesEntity file = filesServices.getFileDetailes(name, relatedObject = "news");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(file.getType())).body(imageData);
    };

    @PostMapping("/add")
    public ResponseEntity<String> uploadForm(
            @RequestParam("title") String title,
            @RequestParam("news") String news,
            @RequestParam(required = false, name = "image") MultipartFile image) {
        final String Resault = newsServices.addNews(title, news, image);
        return ResponseEntity.ok(Resault);

    }

}
