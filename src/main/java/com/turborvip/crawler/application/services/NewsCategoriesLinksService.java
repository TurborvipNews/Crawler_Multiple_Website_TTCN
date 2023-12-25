package com.turborvip.crawler.application.services;

import com.turborvip.crawler.domain.entity.NewsCategoriesLinks;

import java.util.ArrayList;

public interface NewsCategoriesLinksService {
    void saveAll(ArrayList<NewsCategoriesLinks> listLink);

    void save(NewsCategoriesLinks links);

}
