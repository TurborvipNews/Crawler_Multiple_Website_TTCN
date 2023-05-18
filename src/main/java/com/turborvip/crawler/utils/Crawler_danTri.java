package com.turborvip.crawler.utils;

import com.google.gson.Gson;
import com.turborvip.crawler.models.CategoryRespone;
import com.turborvip.crawler.models.News;
import org.apache.hc.core5.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler_danTri {
    public static void main(String[] args) throws IOException, ParseException {
        Request request = new Request("http://localhost:1337/");
        String res = request.get("api/getAllCategories");
        Gson gson = new Gson();
        CategoryRespone[] responseObject = gson.fromJson(res, CategoryRespone[].class);
        System.out.println("A " +responseObject[0].getUrl());
    }

    public static ArrayList<News> getDataCrawlInCategory(String url) throws IOException {
        // TODO Auto-generate method stub
        try {
            ArrayList<News> listNews = new ArrayList<News>();
            String domain = "https://dantri.com.vn";
            //String url = "https://dantri.com.vn/xa-hoi.htm";
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Element component = doc.getElementsByClass("article list").first();

            assert component != null;
            Elements children = component.children();

            Element[] childrenArray = new Element[children.size()];
            children.toArray(childrenArray);

            for (int i = 0; i < childrenArray.length; i++) {
                News data;
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

                data = getDataInDetailPage(link, process);
                data.setCaption(caption);
                data.setDescription(description);
                data.setThumbnail(thumbnail);
                data.setUrl(url);

                /* System.out.println("title" + ": " + child.text());
                System.out.println("description : " + descriptionElement.text());
                System.out.println("img" + ": " + "src :" + imgElement.attr("data-src") + " alt : " + imgElement.attr("alt"));
                System.out.println("url :" + domain+urlElement.attr("href"));*/
                listNews.add(data);
            }
            System.out.println("caption :" + listNews.get(0).getCaption());
            System.out.println("Description :" + listNews.get(0).getDescription());
            System.out.println("Author :" + listNews.get(0).getAuthor());
            System.out.println("Thumbnail :" + listNews.get(0).getThumbnail());
            System.out.println("url :" + listNews.get(0).getUrl());
            System.out.println("Content :" + listNews.get(0).getContent());

            return listNews;
        } catch (IOException error) {
            throw new IOException(error);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static News getDataInDetailPage(String url, int process) throws IOException, InterruptedException {
        News data = new News();
        try {
            System.out.println("---" + process + "%---");
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
}
