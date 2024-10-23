package IUST.EE.Control.Faculties.services.impl;

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
import IUST.EE.Control.Faculties.entity.FacultiesEntity;
import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;
import IUST.EE.Control.Faculties.exceptions.FacultiesExceptions;
import IUST.EE.Control.Faculties.mapper.FacultiesMapper;
import IUST.EE.Control.Faculties.repository.FacultiesRepository;
import IUST.EE.Control.Faculties.services.FacultiesServices;
import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.repository.FilesRepository;

@Service
public class FacultiesServicesImpl implements FacultiesServices {
    private final FacultiesRepository FacultiesRepository;
    private final FilesRepository FilesRepository;
    private final Properties Properties;

    @Autowired
    public FacultiesServicesImpl(FacultiesRepository FacultiesRepository, FilesRepository FilesRepository,
            Properties Properties) {
        this.FacultiesRepository = FacultiesRepository;
        this.FilesRepository = FilesRepository;
        this.Properties = Properties;
    };

    @Override
    public FacultiesEntity Save(FacultiesEntity FacultiesEntity) {
        FacultiesRepository.save(FacultiesEntity);
        return FacultiesEntity;
    };

    @Override
    public FacultiesEntity Find(String id) {
        Optional<FacultiesEntity> Faculties = FacultiesRepository.findById(id);
        if (Faculties.isPresent()) {
            return Faculties.get();
        } else {
            throw new FacultiesExceptions("science committee not found");
        }
    };

    @Override
    public List<FacultiesDto> ShowFaculties() {
        List<FacultiesDto> FacultiesDtos = new ArrayList<>();
        for (FacultiesEntity sc : FacultiesRepository.showFaculties()) {
            FacultiesDtos.add(FacultiesMapper.FacultiesEntityToFacultiesDto(sc));
        }
        return FacultiesDtos;
    };

    private FilesEntity addImage(MultipartFile image, String uuid) {
        @SuppressWarnings("unchecked")
        Map<String, Object> PWD = (Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) Properties.DataStore
                .get("public"))
                .get("faculties"))
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
        imageEntity.setRelatedObject("faculties");
        imageEntity.setType(imageType);
        imageEntity.setIsDeleted(false);
        FilesRepository.save(imageEntity);
        return imageEntity;
    }

    public String addFaculties(String firstName, String lastName, String mail, String description,
            MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String imageName = uuid.toString();
        FacultiesEntity person = new FacultiesEntity();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setIsDeleted(false);
        person.setDescription(description);
        person.setMail(mail);
        person.setImage(addImage(image, imageName));
        this.Save(person);
        return "success";
    };

    public String editFaculties(String id, String firstName, String lastName, String mail, String description,
            MultipartFile image) {
        FacultiesEntity person = this.Find(id);
        if (firstName != null) {
            person.setFirstName(firstName);
        }
        if (lastName != null) {
            person.setLastName(lastName);
        }
        if (mail != null) {
            person.setMail(mail);
        }
        if (description != null) {
            person.setDescription(description);
        }
        if (image != null) {
            String uuid = UUID.randomUUID().toString();
            person.setImage(addImage(image, uuid));
        }
        this.Save(person);
        return id + " Edited successfully";
    }

    public String deleteFaculties(String id) {
        FacultiesEntity person = this.Find(id);
        FacultiesRepository.delete(person);
        return id + " Deleted successfully";
    }

    public String disableFaculties(String id) {
        FacultiesEntity person = this.Find(id);
        person.setIsDeleted(true);
        this.Save(person);
        return id + " Disabled successfully";
    }

    public String enableFaculties(String id) {
        FacultiesEntity person = this.Find(id);
        person.setIsDeleted(false);
        this.Save(person);
        return id + " Enabled successfully";
    }
}
