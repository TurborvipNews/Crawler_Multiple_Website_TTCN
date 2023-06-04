package com.turborvip.crawler.services;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.NewsCategoriesLinks;

import java.util.ArrayList;

public interface NewsCategoriesLinksService {
    void saveAll(ArrayList<NewsCategoriesLinks> listLink);

    void save(NewsCategoriesLinks links);

}
