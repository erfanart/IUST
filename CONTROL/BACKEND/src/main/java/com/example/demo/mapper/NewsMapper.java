package com.example.demo.mapper;

import com.example.demo.dto.NewsDto;
import com.example.demo.entity.News;

public class NewsMapper {
    public static News newsDtoToNews(NewsDto newsDto){
        return News.builder()
                .id(newsDto.getId())
                .title(newsDto.getTitle())
                .news(newsDto.getNews())
                .newsNumber(newsDto.getNewsNumber())
                .image(ImageMapper.imageDtoToImage(newsDto.getImageDto()))
                .build();
    }

    public static NewsDto newsToNewsDto(News news){
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .news(news.getNews())
                .newsNumber(news.getNewsNumber())
                .imageDto(ImageMapper.imageToImageDto(news.getImage()))
                .build();
    }
}
