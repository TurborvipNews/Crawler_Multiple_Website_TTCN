package com.turborvip.crawler.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.News;
import com.turborvip.crawler.models.Url;
import com.turborvip.crawler.repositories.CategoryRepository;
import com.turborvip.crawler.services.CategoryService;
import com.turborvip.crawler.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/test/save/news")
    public void testSave() throws IOException {
//        News news = new News();
//        news.setCaption("test thoi kk");
//        Category category = new Category();
//        category.setId(1L);
//
//        Category category1 = new Category();
//        category1.setId(2L);
//
//        System.out.println(news);
//
//        Set<Category> categories = new HashSet<>();
//        categories.add(category);
//        categories.add(category1);
//
//        news.setLikedNews(categories);

//        this.newsService.saveAll();

//        List<CategoryRepository.FieldNecessaryOfCategory> categories = categoryService.getAll();
//
//        Iterator<CategoryRepository.FieldNecessaryOfCategory> iterator = categories.iterator();
//        while (iterator.hasNext()){
//            CategoryRepository.FieldNecessaryOfCategory element = iterator.next();
//            ArrayList<Url> urlList = element.getUrl();
//            if (urlList != null && !urlList.isEmpty()){
//                for (Url url: urlList) {
//                    System.out.println(url.getType());
//                }
//            }
//            System.out.println("-------------------------");
//        }
//        System.out.println(this.newsService.findById(352L).get());


    }
}
