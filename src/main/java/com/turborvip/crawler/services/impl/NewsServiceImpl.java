package com.turborvip.crawler.services.impl;

import com.turborvip.crawler.models.News;
import com.turborvip.crawler.repositories.NewsInfo;
import com.turborvip.crawler.repositories.NewsRepository;
import com.turborvip.crawler.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void saveAll(ArrayList<News> listNews) {
        this.newsRepository.saveAll(listNews);
    }

    @Override
    public NewsInfo findByCaptionOrUrl(String caption, String url) {
        return  this.newsRepository.findByCaptionOrUrl(caption ,url);
    }

    @Override
    public Optional<News> findById(Long id) {
        return this.newsRepository.findById(id);
    }

    @Override
    public void save(News news) {
        this.newsRepository.save(news);
    }

    @Override
    public void resetViewDay() {
        this.newsRepository.updateView_of_dayBy(0L);
    }

    @Override
    public void resetViewHour() {
        this.newsRepository.updateView_of_hourBy(0L);
    }
}
