package IUST.EE.Control.Labs.services;

import IUST.EE.Control.Labs.entity.LabsEntity;
import IUST.EE.Control.Labs.entity.dto.LabsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LabsServices {

    LabsEntity save(LabsEntity labsEntity);

    LabsEntity findById(String id);

    List<LabsDto> showLabs();

    String addLabs(String name, String place, String head, String description, MultipartFile image);

    String updateLabs(String id, String name, String place, String head, String description, MultipartFile image);

    String deleteLabs(String id);

    String disableLabs(String id);

    String enableLabs(String id);
}
