package IUST.EE.Control.Files.services.impl;

import IUST.EE.Control.Files.entity.FilesEntity;
import IUST.EE.Control.Files.entity.dto.FilesDto;
import IUST.EE.Control.Files.exceptions.FilesExceptions;
import IUST.EE.Control.Files.repository.FilesRepository;
import IUST.EE.Control.Files.services.FilesServices;
import IUST.EE.Control.Files.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilesServicesImpl implements FilesServices {
    private final FilesRepository FilesRepository;

    @Autowired
    public FilesServicesImpl(FilesRepository FilesRepository) {
        this.FilesRepository = FilesRepository;
    }

    @Override
    public FilesEntity Save(FilesEntity FilesEntity) {
        FilesRepository.save(FilesEntity);
        return FilesEntity;
    }

    @Override
    public byte[] download(String relatedObject, String type, String id) {
        try {
            Optional<FilesEntity> fileData = FilesRepository.findFile(relatedObject, type, id);
            String filePath = fileData.get().getFilePath();
            byte[] images = Files.readAllBytes(new File(filePath).toPath());
            return images;
        } catch (IOException e) {
            throw new FilesExceptions(e.getMessage());
        }
    }

    @Override
    public List<FilesDto> ShowFiles(String type, String relatedObject) {
        List<FilesDto> FilesDtos = new ArrayList<>();
        for (FilesEntity sc : FilesRepository.showFiles(type, relatedObject)) {

            FilesDtos.add(FilesMapper.FilesEntityToFilesDto(sc));
        }
        return FilesDtos;
    }
}
