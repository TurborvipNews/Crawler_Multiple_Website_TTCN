package com.turborvip.crawler.utils;

import java.io.IOException;

public  class Crawler {

    public void processCrawl(CrawlStrategy strategy) throws IOException {
        strategy.saveNewsInDB();
    }
}
