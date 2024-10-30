package IUST.EE.Control.Faculties.controller;

// import java.io.File;
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

import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;
import IUST.EE.Control.Faculties.services.FacultiesServices;
import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.services.FilesServices;

@Controller
@RequestMapping("/api/faculties")
public class FacultiesController {
    private final FacultiesServices facultiesServices;
    private final FilesServices filesServices;

    @Autowired
    public FacultiesController(FacultiesServices facultiesServices, FilesServices FilesServices) {
        this.facultiesServices = facultiesServices;
        this.filesServices = FilesServices;
    };

    @GetMapping("/show")
    public ResponseEntity<List<FacultiesDto>> showFaculties() {
        return new ResponseEntity<>(facultiesServices.ShowFaculties(), HttpStatus.ACCEPTED);
    };

    @GetMapping("/image/{name}")
    public ResponseEntity<?> downloadImage(String relatedObject, String type, @PathVariable String name)
            throws IOException {
        byte[] imageData = filesServices.download(relatedObject = "faculties", name);
        FilesEntity file = filesServices.getFileDetailes(name, relatedObject = "faculties");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(file.getType())).body(imageData);
    };

    @PostMapping("/add")
    public ResponseEntity<String> uploadForm(
            @RequestParam("firstName") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {
        final String Resault = facultiesServices.addFaculties(name, lastName, email, description,
                image);
        return ResponseEntity.ok(Resault);

    }

    @PostMapping("/edit")
    public ResponseEntity<String> editForm(
            @RequestParam(required = true, name = "id") String id,
            @RequestParam(required = false, name = "firstName") String name,
            @RequestParam(required = false, name = "lastName") String lastName,
            @RequestParam(required = false, name = "email") String email,
            @RequestParam(required = false, name = "description") String description,
            @RequestParam(required = false, name = "image") MultipartFile image) {
        final String Resault = facultiesServices.editFaculties(id, name, lastName, email, description,
                image);
        return ResponseEntity.ok(Resault);

    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFaculty(@RequestParam("id") String id) {
        final String Resault = facultiesServices.deleteFaculties(id);
        return ResponseEntity.ok(Resault);
    }

    @PostMapping("/disable")
    public ResponseEntity<String> disableFaculty(@RequestParam("id") String id) {
        final String Resault = facultiesServices.disableFaculties(id);
        return ResponseEntity.ok(Resault);
    }

    @PostMapping("/enable")
    public ResponseEntity<String> enableFaculty(@RequestParam(required = false, name = "id") String id) {
        final String Resault = facultiesServices.enableFaculties(id);
        return ResponseEntity.ok(Resault);
    }

};