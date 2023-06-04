package com.turborvip.crawler.services;

import com.turborvip.crawler.models.News;
import com.turborvip.crawler.repositories.NewsInfo;

import java.util.ArrayList;
import java.util.Optional;

public interface NewsService {

    void saveAll(ArrayList<News> listNews);
    NewsInfo findByCaptionOrUrl(String caption, String url);

    Optional<News> findById(Long id);

    void save(News news);
    void resetViewDay();

    void resetViewHour();
}
