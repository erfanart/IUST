package com.example.demo.services.impl;

import com.example.demo.dto.ImageDto;
import com.example.demo.dto.NewsDto;
import com.example.demo.entity.News;
import com.example.demo.exceptions.ImageException;
import com.example.demo.exceptions.NewsException;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.repository.NewsRepository;
import com.example.demo.services.ImageService;
import com.example.demo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ImageService imageService;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, ImageService imageService) {
        this.newsRepository = newsRepository;
        this.imageService = imageService;
    }

    @Override
    public News save(News news) {
        newsRepository.save(news);
        return news;
    }

    @Override
    public News findById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()){
            return news.get();
        }else {
            throw new NewsException("news not found");
        }
    }

    @Override
    public List<NewsDto> showNews() {
        List<NewsDto> newsDtos = new ArrayList<>();
        for (News n : newsRepository.showNews()
        ) {
            newsDtos.add(NewsMapper.newsToNewsDto(n));
        }
        return newsDtos;
    }
}
