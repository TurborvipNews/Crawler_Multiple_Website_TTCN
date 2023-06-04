package com.turborvip.crawler.services.impl;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.NewsCategoriesLinks;
import com.turborvip.crawler.repositories.NewsCategoriesLinksRepository;
import com.turborvip.crawler.services.NewsCategoriesLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsCategoriesServiceImpl implements NewsCategoriesLinksService {
    @Autowired
    NewsCategoriesLinksRepository newsCategoriesLinksRepository;
    @Override
    public void saveAll(ArrayList<NewsCategoriesLinks> listLink) {
        this.newsCategoriesLinksRepository.saveAll(listLink);
    }

    @Override
    public void save(NewsCategoriesLinks links) {
        this.newsCategoriesLinksRepository.save(links);
    }
}
