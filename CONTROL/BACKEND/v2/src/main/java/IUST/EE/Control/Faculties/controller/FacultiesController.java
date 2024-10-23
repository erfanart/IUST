package IUST.EE.Control.Faculties.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import IUST.EE.Control.Properties;
import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;
import IUST.EE.Control.Faculties.services.FacultiesServices;
import IUST.EE.Control.Files.services.FilesServices;

@Controller
@RequestMapping("/api/faculties")
public class FacultiesController {
    private final FacultiesServices FacultiesServices;
    private final FilesServices FilesServices;

    @Autowired
    public FacultiesController(FacultiesServices FacultiesServices, FilesServices FilesServices,
            Properties Properties) {
        this.FacultiesServices = FacultiesServices;
        this.FilesServices = FilesServices;
    };

    @GetMapping("/show")
    public ResponseEntity<List<FacultiesDto>> showFaculties() {
        return new ResponseEntity<>(FacultiesServices.ShowFaculties(), HttpStatus.ACCEPTED);
    };

    @GetMapping("/image/{name}")
    public ResponseEntity<?> downloadImage(String relatedObject, String type, @PathVariable String name)
            throws IOException {
        byte[] imageData = FilesServices.download(relatedObject = "faculties", type = "image/png", name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    };

    @PostMapping("/add")
    public ResponseEntity<String> uploadForm(
            @RequestParam("firstName") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {
        final String Resault = FacultiesServices.addFaculties(name, lastName, email, description,
                image);
        return ResponseEntity.ok(Resault);

    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFaculty(@RequestParam("id") String id) {
        final String Resault = FacultiesServices.deleteFaculties(id);
        return ResponseEntity.ok(Resault);
    }

    @PostMapping("/disable")
    public ResponseEntity<String> disableFaculty(@RequestParam("id") String id) {
        final String Resault = FacultiesServices.disableFaculties(id);
        return ResponseEntity.ok(Resault);
    }

    @PostMapping("/enable")
    public ResponseEntity<String> enableFaculty(@RequestParam("id") String id) {
        final String Resault = FacultiesServices.enableFaculties(id);
        return ResponseEntity.ok(Resault);
    }

}