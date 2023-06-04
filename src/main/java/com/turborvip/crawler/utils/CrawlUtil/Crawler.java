package com.turborvip.crawler.utils.CrawlUtil;

import java.io.IOException;

public  class Crawler {

    public void processCrawl(CrawlStrategy strategy) throws IOException, InterruptedException {
        strategy.saveNewsInDB();
    }
}
