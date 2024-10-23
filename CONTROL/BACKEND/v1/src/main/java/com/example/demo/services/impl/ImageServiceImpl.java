package com.example.demo.services.impl;

import com.example.demo.entity.Image;
import com.example.demo.exceptions.ImageException;
import com.example.demo.repository.ImageRepository;
import com.example.demo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public Image save(Image image) {
        imageRepository.save(image);
        return image;
    }

    @Override
    public Image findById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()){
            return image.get();
        }else {
            throw new ImageException("image not found");
        }
    }
    @Override
    public byte[] downloadImage(String name) {
        try {
            Optional<Image> fileData = imageRepository.findByName(name);
            String filePath = fileData.get().getFilePath();
            byte[] images = Files.readAllBytes(new File(filePath).toPath());
            return images;
        } catch (IOException e) {
            throw new ImageException(e.getMessage());
        }

    }

}
