package com.turborvip.crawler.scheduler;

import com.turborvip.crawler.models.Url;
import com.turborvip.crawler.repositories.CategoryRepository;
import com.turborvip.crawler.services.CategoryService;
import com.turborvip.crawler.services.NewsService;
import com.turborvip.crawler.utils.CrawlUtil.CrawlByDanTri;
import com.turborvip.crawler.utils.CrawlUtil.CrawlStrategy;
import com.turborvip.crawler.utils.CrawlUtil.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CrawlerNewsTask {
    CrawlStrategy strategy;

    @Autowired
    NewsService newsService;
    @Autowired
    CategoryService categoryService;

    @Scheduled(fixedRate = 600000)
    public  void crawlerNews() throws IOException {
        System.out.println("Crawling news ..... ");

        List<CategoryRepository.FieldNecessaryOfCategory> categories = categoryService.getAll();

        for (CategoryRepository.FieldNecessaryOfCategory category : categories) {
            ArrayList<Url> urlList = category.getUrl();
            if (urlList != null && !urlList.isEmpty()) {
                for (Url url : urlList) {
                    Crawler crawler = new Crawler();
                    System.out.println(category.getName() + " in " + url.getType());
                    if(url.getType().equals("dan_tri")){
                        System.out.println("Crawl dan tri strategy " + url.getPath());
                        strategy = new CrawlByDanTri(this.newsService,url.getPath(),category.getId());
                    }
                    assert strategy != null;
                    crawler.processCrawl(strategy);
                }
            }
            System.out.println("-------------------------");
        }
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public static void resetViewOfDay() {
        System.out.println("Resetting view of day .......");
    }

    @Scheduled(cron = "0 0 */2 * * ?")
    public static void resetViewOfHour() {
        System.out.println("Resetting view of hour .......");
    }
}
