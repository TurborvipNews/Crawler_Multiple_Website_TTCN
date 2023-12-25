package com.turborvip.crawler.adapter.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface NewsResource {
    @GetMapping("/news")
    ResponseEntity<?> getNews(HttpServletRequest request);

    @GetMapping("/news/newest")
    ResponseEntity<?> getNewsNewest(HttpServletRequest request);

    @GetMapping("/news/hottest")
    ResponseEntity<?> getNewsHottest(HttpServletRequest request);

    @GetMapping("/news/infinitive")
    ResponseEntity<?> getNewsInfinitive(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size, HttpServletRequest request);

    @GetMapping("/news/{newsId}")
    ResponseEntity<?> getNewsDetail(@PathVariable("newsId") Long newsId,HttpServletRequest request);

    @GetMapping("/category/{fieldCategory}")
    ResponseEntity<?> getNewsFollowCategory(@PathVariable("fieldCategory") String fieldCategory,
                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                            @RequestParam(name = "caption", required = false) String caption,
                                            @RequestParam(name = "author", required = false) String author,
                                            @RequestParam(name = "filter", required = false, defaultValue = "DESC") String filter,
                                            HttpServletRequest request);
}
