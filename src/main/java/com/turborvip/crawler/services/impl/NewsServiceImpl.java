package com.turborvip.crawler.services.impl;

import com.turborvip.crawler.models.News;
import com.turborvip.crawler.repositories.NewsRepository;
import com.turborvip.crawler.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void save(News news) {
        this.newsRepository.save(news);
    }

    @Override
    public void resetView() {

    }
}
