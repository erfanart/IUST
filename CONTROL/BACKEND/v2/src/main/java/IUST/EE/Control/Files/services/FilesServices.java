package IUST.EE.Control.Files.services;

import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;

import java.util.List;

public interface FilesServices {
    FilesEntity Save(FilesEntity FilesEntity);
    FilesEntity getFileDetailes(String id,String relatedObject);

    byte[] download(String relatedObject, String id);
    String deleteFile(String id,String relatedObject);
    List<FilesDto> ShowFiles(String type, String relatedObject);
}
