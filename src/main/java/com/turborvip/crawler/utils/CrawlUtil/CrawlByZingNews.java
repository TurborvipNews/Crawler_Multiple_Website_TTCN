package com.turborvip.crawler.utils.CrawlUtil;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.News;
import com.turborvip.crawler.models.NewsCategoriesLinks;
import com.turborvip.crawler.repositories.NewsInfo;
import com.turborvip.crawler.services.CategoryService;
import com.turborvip.crawler.services.NewsCategoriesLinksService;
import com.turborvip.crawler.services.NewsService;
import com.turborvip.crawler.utils.ProgressUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class CrawlByZingNews implements CrawlStrategy {
    private NewsService newsService;
    private NewsCategoriesLinksService newsCategoriesLinksService;
    private String path;
    private Optional<Category> category;
    private Long categoryId;
    private static final String domain = "https://zingnews.vn";

    private final int limitElement = 9;

    @Override
    public ArrayList<News> getDataListNews(String path) {
        try {
            ArrayList<News> listNews = new ArrayList<>();

            Document doc = Jsoup.connect(path).timeout(5000).get();
            Element componentWrapper = doc.getElementById("news-latest");

            if (componentWrapper == null) {
                System.out.println("\n Error with path " + path + "\n");
                return null;
            }

            Element component = componentWrapper.getElementsByClass("article-list").first();

            Elements children = component.children();

            // limit element you want to crawl
            Elements elements = new Elements();
            for (int i = 0; i < Math.min(this.limitElement, children.size()); i++) {
                elements.add(children.get(i));
            }
            Element[] childrenArray = new Element[elements.size()];

            // convert to array to loop
            elements.toArray(childrenArray);

            // loop to get detail content, author,... and add create all attribute to a news and add it to list news
            for (int i = 0; i < childrenArray.length; i++) {
                News news = new News();
                Element imgElement = Objects.requireNonNull(childrenArray[i].getElementsByClass("article-thumbnail").first())
                        .getElementsByTag("img").first();
                Element descriptionElement = childrenArray[i].getElementsByClass("article-summary").first();
                Element urlElement = childrenArray[i].getElementsByTag("a").first();

                if (urlElement != null) {
                    String link = domain + urlElement.attr("href");
                    String caption = childrenArray[i].text();
                    String description = null, thumbnail = null;
                    if (descriptionElement != null) {
                        description = descriptionElement.text();
                    }
                    if (imgElement != null) {
                        thumbnail = imgElement.absUrl("data-src");
                    }
                    int process = Math.round((float) ((i + 1) * 100) / childrenArray.length);

                    news = this.getDataDetailPage(link, process);
                    news.setCaption(caption);
                    news.setDescription(description);
                    news.setThumbnail(thumbnail);
                    news.setUrl(link);
                }
                if (news.getContent() != null && news.getThumbnail() != null) {
                    listNews.add(news);
                }
            }
            return listNews;
        } catch (IOException error) {
            System.out.println("1");
            return null;
        }
    }

    @Override
    public News getDataDetailPage(String url, int process) {
        News data = new News();
        try {
            ProgressUtil p = new ProgressUtil();
            p.showProgress(process);
            Thread.sleep(2000);
            Document doc = Jsoup.connect(url).timeout(20000).get();
            Element authorElement = doc.getElementsByClass("the-article-author").first();
            Element contentElement = doc.getElementsByClass("the-article-body").first();
            String author = authorElement != null ? authorElement.text() : null;
            String content = contentElement != null ? contentElement.outerHtml() : null;
            data.setAuthor(author);
            data.setContent(content);
            return data;
        } catch (IOException | InterruptedException err) {
            System.out.println("Err in getDataDetailPage , err networking wifi, wifi lag");
            return null;
        }
    }

    @Override
    public void saveNewsInDB() {
        ArrayList<News> listNews = this.getDataListNews(path);
        if (listNews == null) {
            System.out.print("\nCrawl 0 items\n");
            System.out.println("\n---------------------------------------------------------------------------------\n");
        } else {
            ArrayList<NewsCategoriesLinks> links = new ArrayList<>();
            ArrayList<NewsCategoriesLinks> linksUpdate = new ArrayList<>();
            ArrayList<News> listNewsNotExist = new ArrayList<>();
            ArrayList<News> listNewsExist = new ArrayList<>();

            for (News news : listNews) {
                NewsInfo newsExist = this.newsService.findByCaptionOrUrl(news.getCaption(), news.getUrl());
                if (newsExist == null) {
                    // TODO save news to DB, check and update category with link
                    NewsCategoriesLinks link = new NewsCategoriesLinks();
                    if (this.category.isPresent()) {
                        Category categoryExist = this.category.get();
                        link.setNews(news);
                        link.setCategory(categoryExist);
                        links.add(link);
                    }
                    listNewsNotExist.add(news);

                } else {
                    // TODO check include category
                    boolean checkIncludeCategory = false;
                    for (NewsInfo.NewsCategoriesLinksInfo info : newsExist.getLinks()) {
                        if (Objects.equals(info.getCategory().getId(), this.categoryId)) {
                            checkIncludeCategory = true;
                            break;
                        }
                    }

                    if (!checkIncludeCategory) {
                        // ToDo update links category
                        NewsCategoriesLinks link = new NewsCategoriesLinks();
                        News newsExistToMerge = this.newsService.findById(newsExist.getId()).orElse(null);
                        if (this.category.isPresent()) {
                            Category categoryExist = this.category.get();
                            link.setNews(newsExistToMerge);
                            link.setCategory(categoryExist);
                            linksUpdate.add(link);
                            listNewsExist.add(newsExistToMerge);
                        }
                    }
                }
            }
            if (links.size() > 0) {
                this.newsService.saveAll(listNewsNotExist);
                this.newsCategoriesLinksService.saveAll(links);
                System.out.print("\n Crawl success " + links.size() + " items !\n");
            } else {
                System.out.print("\nCrawl 0 items\n");
            }

            if (linksUpdate.size() > 0) {
                this.newsService.saveAll(listNewsExist);
                this.newsCategoriesLinksService.saveAll(linksUpdate);
                System.out.print("\n" + "Update " + linksUpdate.size() + " news\n");
            }
        }
    }

    @Autowired
    public CrawlByZingNews(NewsService newsService, CategoryService categoryService, NewsCategoriesLinksService newsCategoriesLinksService, String path, Long categoryId) {
        try {
            this.newsService = newsService;
            this.newsCategoriesLinksService = newsCategoriesLinksService;
            this.path = path;
            this.category = categoryService.findById(categoryId);
            this.categoryId = categoryId;
        } catch (Exception err) {
            System.out.println("Fails");
        }

    }
}
