package com.turborvip.crawler.utils.CrawlUtil;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.News;
import com.turborvip.crawler.services.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CrawlByDanTri implements CrawlStrategy {

    private NewsService newsService;

    private String path;

    private Long categoryId;

    private static final String domain = "https://dantri.com.vn";

    @Override
    public ArrayList<News> getDataListNews(String path) throws IOException {
        try {
            ArrayList<News> listNews = new ArrayList<>();
            assert path != null;
            Document doc = Jsoup.connect(path).timeout(5000).get();
            Element component = doc.getElementsByClass("article list").first();

            assert component != null;
            Elements children = component.children();

            Element[] childrenArray = new Element[children.size()];
            children.toArray(childrenArray);

            for (int i = 0; i < childrenArray.length; i++) {
                News news;
                Element imgElement = childrenArray[i].getElementsByTag("img").first();
                Element descriptionElement = childrenArray[i].getElementsByClass("article-excerpt").first();
                Element urlElement = childrenArray[i].getElementsByTag("a").first();

                assert urlElement != null;
                String link = domain + urlElement.attr("href");
                String caption = childrenArray[i].text();
                assert descriptionElement != null;
                String description = descriptionElement.text();
                assert imgElement != null;
                String thumbnail = imgElement.attr("data-src");

                int process = Math.round((float) ((i + 1) * 100) / childrenArray.length);

                news = this.getDataDetailPage(link, process);
                news.setCaption(caption);
                news.setDescription(description);
                news.setThumbnail(thumbnail);
                news.setUrl(path);

                listNews.add(news);
            }
            return listNews;
        } catch (IOException error) {
            throw new IOException(error);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public News getDataDetailPage(String url, int process) throws IOException, InterruptedException {
        News data = new News();
        try {
            System.out.print("---" + process +"%");
            Thread.sleep(3000);
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Element authorElement = doc.getElementsByClass("author-name").first();
            Element contentElement = doc.getElementsByClass("singular-content").first();
            String author = authorElement != null ? authorElement.text() : null;
            String content = contentElement != null ? contentElement.outerHtml() : null;
            data.setAuthor(author);
            data.setContent(content);
            return data;
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void saveNewsInDB() throws IOException {
        ArrayList<News> listNews = this.getDataListNews(path);
        ArrayList<News> listNewsNotExist = new ArrayList<>();
        for (News news : listNews) {
            // create category current
            Category category = new Category();
            category.setId(this.categoryId);
            Set<Category> categories = new HashSet<>();
            categories.add(category);
            // create reference with category
            news.setLikedNews(categories);
            // create record in database
            News newsExist = this.newsService.findByCaptionAndUrl(news.getCaption(), news.getUrl());
            if (newsExist == null) {
                listNewsNotExist.add(news);
            }
        }
        if (listNewsNotExist.size()>0){
                this.newsService.saveAll(listNewsNotExist);
            System.out.printf("\n"+" Crawl success "+ listNewsNotExist.size() +" items !" + "\n");
        }else {
            System.out.println("Crawl 0 items");
        }

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
//
//        this.newsService.save(news);
    }

    @Autowired
    public CrawlByDanTri(NewsService newsService, String path, Long categoryId) {
        this.newsService = newsService;
        this.path = path;
        this.categoryId = categoryId;
    }
}
