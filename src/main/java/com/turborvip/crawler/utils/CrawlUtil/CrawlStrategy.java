package com.turborvip.crawler.utils.CrawlUtil;

import com.turborvip.crawler.models.News;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface CrawlStrategy {
    public ArrayList<News> getDataListNews(String url) throws IOException, ParseException;

    public News getDataDetailPage(String url, int process) throws IOException, InterruptedException;

    public void saveNewsInDB() throws IOException;
}
