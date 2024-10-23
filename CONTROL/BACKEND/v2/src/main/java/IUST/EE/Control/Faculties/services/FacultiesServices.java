package IUST.EE.Control.Faculties.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import IUST.EE.Control.Faculties.entity.FacultiesEntity;
import IUST.EE.Control.Faculties.entity.dto.FacultiesDto;

public interface FacultiesServices {
    FacultiesEntity Save(FacultiesEntity FacultiesEntity);

    FacultiesEntity Find(String id);

    List<FacultiesDto> ShowFaculties();

    String addFaculties(String firstName, String lastName, String mail, String description,
            MultipartFile image);

    String editFaculties(String id, String firstName, String lastName, String mail, String description,
            MultipartFile image);

    String deleteFaculties(String id);

    String disableFaculties(String id);

    String enableFaculties(String id);

}
