package com.turborvip.crawler.adapter.web.rest.impl;

import com.turborvip.crawler.adapter.web.base.RestApiV1;
import com.turborvip.crawler.application.https.response.NewsByCategoryResponse;
import com.turborvip.crawler.application.services.CategoryService;
import com.turborvip.crawler.domain.dto.*;
import com.turborvip.crawler.application.https.response.NewsResponse;
import com.turborvip.crawler.application.services.NewsService;
import com.turborvip.crawler.adapter.web.base.VsResponseUtil;
import com.turborvip.crawler.adapter.web.rest.NewsResource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestApiV1
@RequiredArgsConstructor
public class NewsResourceImpl implements NewsResource {
    @Autowired
    private final NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<?> getNews(HttpServletRequest request) {
        try {
            NewsResponse data = new NewsResponse(newsService.findNews());
            List<NewsDTO> array = data.getNews();
            return VsResponseUtil.ok("ok", array);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> getNewsNewest(HttpServletRequest request) {
        try {
            List<NewsNewestDTO> array = newsService.findNewest();
            return VsResponseUtil.ok("success", array);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getNewsHottest(HttpServletRequest request) {
        try {
            List<NewsHottestDTO> array = newsService.findNewsHottest();
            return VsResponseUtil.ok("success", array);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getNewsInfinitive(Integer page, Integer size, HttpServletRequest request) {
        try {
            List<NewsNewestDTO> array = newsService.findNewsInfinitive(page, size);
            return VsResponseUtil.ok("success", array);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getNewsDetail(Long newsId, HttpServletRequest request) {
        try {
            NewsDetailDTO data = newsService.findNewsDetail(newsId).orElseThrow();
            return VsResponseUtil.ok("success", data);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getNewsFollowCategory(String fieldCategory, Integer page, Integer size, String caption, String author, String filter, HttpServletRequest request) {
        try {
            List<NewsNewestDTO> listNews = newsService.findNewsFollowCategory(page-1, size, fieldCategory, filter, caption, author);
            CategoryDTO categoryDTO = categoryService.findByField(fieldCategory).orElseThrow();
            float totalPage = (float) newsService.countNewsByFieldCategory(fieldCategory) / size;
            NewsByCategoryResponse newsByCategoryResponse = new NewsByCategoryResponse(listNews, categoryDTO, Math.round(totalPage));
            return VsResponseUtil.ok("success", newsByCategoryResponse);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }


}
