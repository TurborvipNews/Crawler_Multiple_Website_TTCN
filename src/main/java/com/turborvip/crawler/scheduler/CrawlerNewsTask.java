package com.turborvip.crawler.scheduler;

import com.turborvip.crawler.models.Url;
import com.turborvip.crawler.repositories.CategoryRepository;
import com.turborvip.crawler.services.CategoryService;
import com.turborvip.crawler.services.NewsCategoriesLinksService;
import com.turborvip.crawler.services.NewsService;
import com.turborvip.crawler.utils.CrawlUtil.CrawlByDanTri;
import com.turborvip.crawler.utils.CrawlUtil.CrawlByZingNews;
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

    @Autowired
    NewsCategoriesLinksService newsCategoriesLinksService;

    // TODO crawler news follow category get from DB
    @Scheduled(fixedRate = 1800000)
    public void crawlerNews() throws IOException, InterruptedException {
        try {
            System.out.println("Crawling news ..... ");
            List<CategoryRepository.FieldNecessaryOfCategory> categories = categoryService.getAll();

            for (CategoryRepository.FieldNecessaryOfCategory category : categories) {
                ArrayList<Url> urlList = category.getUrl();
                if (urlList != null && !urlList.isEmpty()) {
                    for (Url url : urlList) {
                        Crawler crawler = new Crawler();
                        System.out.println(category.getName() + " in " + url.getType());

                        if (url.getType().equals("dan_tri")) {
                            System.out.println("Crawl dan tri strategy " + url.getPath());
                            strategy = new CrawlByDanTri(this.newsService, this.categoryService, this.newsCategoriesLinksService, url.getPath(), category.getId());
                        }
                        if (url.getType().equals("zing_news")) {
                            System.out.println("Crawl zing news " + url.getPath());
                            strategy = new CrawlByZingNews(this.newsService, this.categoryService, this.newsCategoriesLinksService, url.getPath(), category.getId());
                        }

                        if (strategy != null) {
                            crawler.processCrawl(strategy);
                            strategy = null;
                        } else {
                            System.out.println("System isn't support for " + url.getType());
                        }
                    }
                    System.out.println("\n---------------------------------------------------------------------------------\n");
                }
            }
            System.out.println("*****************************End task crawler**************************************");
        } catch (IOException err) {
            throw new IOException(err);
        } catch (InterruptedException err) {
            throw new InterruptedException();
        }

    }

    // TODO Reset viewOfDay = 0
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetViewOfDay() {
        this.newsService.resetViewDay();
        System.out.println("\n" + "Resetting view of day .......");
    }

    // TODO Reset viewOfHour = 0
    @Scheduled(cron = "0 0 */2 * * ?")
    public void resetViewOfHour() {
        this.newsService.resetViewHour();
        System.out.println("\n" + "Resetting view of hour .......");
    }

    // TODO Check all new in DB and if News =
}
