package IUST.EE.Control.Files.services;

import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;

import java.util.List;

public interface FilesServices {
    FilesEntity Save(FilesEntity FilesEntity);

    // FilesEntity Find(Long id);
    byte[] download(String relatedObject, String type, String name);

    List<FilesDto> ShowFiles(String type, String relatedObject);
}
