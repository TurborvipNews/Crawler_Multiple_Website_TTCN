package com.turborvip.crawler.services.impl;

import com.turborvip.crawler.models.News;
import com.turborvip.crawler.repositories.NewsRepository;
import com.turborvip.crawler.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public void saveAll(ArrayList<News> news) {
        this.newsRepository.saveAllAndFlush(news);
    }
    @Override
    public News findByCaptionAndUrl(String caption, String url) {
        return this.newsRepository.findByCaptionAndUrl(caption ,url);
    }
    @Override
    public void resetView() {

    }

}
