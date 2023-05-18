package com.turborvip.crawler.services;

import com.turborvip.crawler.models.News;

public interface NewsService {
    void save(News news);
    void resetView();
}
