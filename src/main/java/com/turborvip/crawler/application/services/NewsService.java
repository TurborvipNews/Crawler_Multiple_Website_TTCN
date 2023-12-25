package com.turborvip.crawler.application.services;

import com.turborvip.crawler.domain.dto.NewsDTO;
import com.turborvip.crawler.domain.dto.NewsDetailDTO;
import com.turborvip.crawler.domain.dto.NewsHottestDTO;
import com.turborvip.crawler.domain.dto.NewsNewestDTO;
import com.turborvip.crawler.domain.entity.News;
import com.turborvip.crawler.application.repositories.NewsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    void saveAll(ArrayList<News> listNews);
    NewsInfo findByCaptionOrUrl(String caption, String url);
    Optional<News> findById(Long id);
    void save(News news);
    void resetViewDay();
    void resetViewHour();
    List<NewsDTO> findNews();
    List<NewsNewestDTO> findNewest();
    List<NewsHottestDTO> findNewsHottest();
    List<NewsNewestDTO> findNewsInfinitive(Integer page, Integer size);
    Optional<NewsDetailDTO> findNewsDetail(Long id);
    List<NewsNewestDTO> findNewsFollowCategory(Integer page, Integer size,String fieldCategory,String filter,String caption, String author);
    Long countNewsByFieldCategory(String field);
}
