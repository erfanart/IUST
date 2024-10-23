package com.example.demo.services;

import com.example.demo.entity.Image;

public interface ImageService {
    Image save(Image image);
    Image findById(Long Id);
    byte[] downloadImage(String name);
}
