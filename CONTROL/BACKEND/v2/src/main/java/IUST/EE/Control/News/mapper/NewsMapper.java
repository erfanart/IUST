package IUST.EE.Control.News.mapper;

import IUST.EE.Control.News.entity.NewsEntity;
import IUST.EE.Control.News.entity.dto.NewsDto;
import IUST.EE.Control.Files.mapper.FilesMapper;

public class NewsMapper {
    public static NewsEntity newsDtoToNewsRntity(NewsDto NewsDto) {
        return NewsEntity.builder()
                .id(NewsDto.getId())
                .title(NewsDto.getTitle())
                .news(NewsDto.getNews())
                .newsNumber(NewsDto.getNewsNumber())
                .image(FilesMapper.FilesDtoToFilesEntity(NewsDto.getImage()))
                .build();
    }

    public static NewsDto newsEntityToNewsDto(NewsEntity newsEntity) {
        return NewsDto.builder()
                .id(newsEntity.getId())
                .title(newsEntity.getTitle())
                .news(newsEntity.getNews())
                .newsNumber(newsEntity.getNewsNumber())
                .image(FilesMapper.FilesEntityToFilesDto(newsEntity.getImage()))
                .build();

    }

}
