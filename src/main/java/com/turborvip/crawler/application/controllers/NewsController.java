package com.turborvip.crawler.application.controllers;

import com.turborvip.crawler.application.services.CategoryService;
import com.turborvip.crawler.application.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

//    @GetMapping("/test/save/news")
//    public void testSave() throws IOException {
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
//    }
}
