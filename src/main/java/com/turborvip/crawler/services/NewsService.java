package com.turborvip.crawler.services;

import com.turborvip.crawler.models.News;

import java.util.ArrayList;

public interface NewsService {
    void saveAll(ArrayList<News> news);
    News findByCaptionAndUrl(String caption,String url);
    void resetView();
}
