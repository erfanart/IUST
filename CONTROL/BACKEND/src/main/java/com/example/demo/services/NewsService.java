package com.example.demo.services;

import com.example.demo.dto.NewsDto;
import com.example.demo.entity.News;

import java.util.List;

public interface NewsService {
    News save(News news);
    News findById(Long id);
    List<NewsDto> showNews();

}
