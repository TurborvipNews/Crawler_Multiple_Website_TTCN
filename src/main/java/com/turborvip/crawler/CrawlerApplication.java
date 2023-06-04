package com.turborvip.crawler;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CrawlerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
