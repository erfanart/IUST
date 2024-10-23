package com.example.demo.mapper;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Image;

public class ImageMapper {
    public static Image imageDtoToImage(ImageDto imageDto){
        return Image.builder()
                .id(imageDto.getId())
                .type(imageDto.getType())
                .build();
    }
    public static ImageDto imageToImageDto(Image image){
        return ImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .type(image.getType())
                .build();
    }
}
