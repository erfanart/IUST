package IUST.EE.Control.Labs.services.impl;


import IUST.EE.Control.Properties;
import IUST.EE.Control.Labs.entity.LabsEntity;
import IUST.EE.Control.Labs.entity.dto.LabsDto;
import IUST.EE.Control.Labs.exceptions.LabsException;
import IUST.EE.Control.Labs.mapper.LabsMapper;

import IUST.EE.Control.Labs.repository.LabsRepository;
import IUST.EE.Control.Labs.services.LabsServices;
import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.exceptions.FilesExceptions;
import IUST.EE.Control.Files.repository.FilesRepository;
import IUST.EE.Control.Files.services.FilesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class LabsServicesImpl implements LabsServices {

    private final LabsRepository labsRepository;
    private final FilesRepository filesRepository;
    private final FilesServices filesServices;
    private final Properties properties;


    @Autowired
    public LabsServicesImpl(LabsRepository labsRepository,
                            FilesRepository filesRepository,
                            FilesServices filesServices,
                            Properties properties) {
        this.labsRepository = labsRepository;
        this.filesRepository = filesRepository;
        this.filesServices = filesServices;
        this.properties = properties;
    }


    @Override
    public LabsEntity save(LabsEntity labsEntity) {
        labsRepository.save(labsEntity);
        return labsEntity;
    }

    @Override
    public LabsEntity findById(String id) {
        Optional<LabsEntity> lab = labsRepository.findById(id);
        if (lab.isPresent()) {
            return lab.get();
        } else {
            throw new LabsException("Laboratory not found");
        }
    }

    @Override
    public List<LabsDto> showLabs() {
        List<LabsDto> labsDtos = new ArrayList<>();
        for (LabsEntity l : labsRepository.showLabs()) {
            labsDtos.add(LabsMapper.labsEntityToLabsDto(l));
        }
        return labsDtos;
    }


    private FilesEntity addImage(MultipartFile image, String uuid) {
        @SuppressWarnings("unchecked")
        Map<String, Object> PWD = (Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) properties.DataStore
                .get("public"))
                .get("labs"))
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
        imageEntity.setRelatedObject("labs");
        imageEntity.setType(imageType);
        imageEntity.setIsDeleted(false);
        filesRepository.save(imageEntity);
        return imageEntity;
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
    public String addLabs(String name, String place, String head, String description, MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String imageNmae = uuid.toString();
        this.save(LabsEntity.builder()
                .name(name)
                .place(place)
                .description(head)
                .image(addImage(image, imageNmae))
                .isDeleted(false)
                .build());
        return "laboratory added";

    }


    @Override
    public String updateLabs(String id, String name, String place, String head, String description, MultipartFile image) {
        try {
            if (id == null) {
                throw new LabsException("please enter id");
            }
            LabsEntity lab = this.findById(id);
            if (name != null) {
                lab.setName(name);
            }
            if (place != null) {
                lab.setPlace(place);
            }
            if (head != null) {
                lab.setHead(head);
            }
            if (description != null) {
                lab.setDescription(description);
            }
            if (image != null) {
                String uuid = UUID.randomUUID().toString();
                FilesEntity lastImage = lab.getImage();
                lab.setImage(addImage(image, uuid));
                filesServices.deleteFile(this.deleteImage(lastImage).getId(), "labs");
            }
            this.save(lab);

            return "labratory with id " + id + " updated";

        } catch (Exception e) {
            throw new LabsException(e.getMessage());
        }
    }


    @Override
    public String deleteLabs(String id) {
        try {
            LabsEntity lab = this.findById(id);
            labsRepository.delete(lab);
            return "labratory with id " + id + " deleted";
        } catch (Exception e) {
            throw new LabsException(e.getMessage());
        }

    }


    @Override
    public String disableLabs(String id) {
        try {
            LabsEntity lab = this.findById(id);
            lab.setIsDeleted(true);
            this.save(lab);
            return "labratory with id " + id + " disabled";
        } catch (Exception e) {
            throw new LabsException(e.getMessage());
        }

    }


    @Override
    public String enableLabs(String id) {
        try {
            LabsEntity lab = this.findById(id);
            lab.setIsDeleted(false);
            this.save(lab);
            return "labratory with id " + id + " enabled";
        }catch (Exception e){
            throw new LabsException(e.getMessage());
        }
    }

}
