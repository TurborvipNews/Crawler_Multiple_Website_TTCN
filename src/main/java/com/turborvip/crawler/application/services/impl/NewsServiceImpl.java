package com.turborvip.crawler.application.services.impl;

import com.turborvip.crawler.domain.dto.NewsDTO;
import com.turborvip.crawler.domain.dto.NewsDetailDTO;
import com.turborvip.crawler.domain.dto.NewsHottestDTO;
import com.turborvip.crawler.domain.dto.NewsNewestDTO;
import com.turborvip.crawler.domain.entity.News;
import com.turborvip.crawler.application.repositories.NewsInfo;
import com.turborvip.crawler.application.repositories.NewsRepository;
import com.turborvip.crawler.application.services.NewsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void saveAll(ArrayList<News> listNews) {
        this.newsRepository.saveAll(listNews);
    }

    @Override
    public NewsInfo findByCaptionOrUrl(String caption, String url) {
        return  this.newsRepository.findByCaptionOrUrl(caption ,url);
    }

    @Override
    public Optional<News> findById(Long id) {
        return this.newsRepository.findById(id);
    }

    @Override
    public void save(News news) {
        this.newsRepository.save(news);
    }

    @Override
    public void resetViewDay() {
        this.newsRepository.updateView_of_dayBy(0L);
    }

    @Override
    public void resetViewHour() {
        this.newsRepository.updateView_of_hourBy(0L);
    }

    @Override
    public List<NewsDTO> findNews() {
        List<NewsDTO> data = this.newsRepository.findByStatus(true, PageRequest.of(0,1));
        System.out.println(data.size());
        return data;
    }

    @Override
    public List<NewsNewestDTO> findNewest() {
        return this.newsRepository.findByStatusOrderByCreatedAtDesc(true, PageRequest.of(0,7));
    }

    @Override
    public List<NewsHottestDTO> findNewsHottest() {
        return this.newsRepository.findByStatusOrderByViewOfHourDesc(true, PageRequest.of(0,5));
    }

    @Override
    public List<NewsNewestDTO> findNewsInfinitive(Integer page, Integer size) {
        return this.newsRepository.findByStatusOrderByCreatedAtDesc(true, PageRequest.of(page,size));
    }

    @Override
    public Optional<NewsDetailDTO> findNewsDetail(Long id) {
        return this.newsRepository.findNewsDetailDTOByStatusAndId(true,id);
    }

    @Override
    public List<NewsNewestDTO> findNewsFollowCategory(Integer page, Integer size,String fieldCategory, String filter, String caption, String author) {
        if(filter.equals("asc") || filter.equals("ASC")){
            return this.newsRepository.findByLinks_Category_FieldAndStatus(fieldCategory,true,PageRequest.of(page,size,Sort.by("createdAt").ascending()));
        }else{
            return this.newsRepository.findByLinks_Category_FieldAndStatus(fieldCategory,true,PageRequest.of(page,size,Sort.by("createdAt").descending()));
        }
    }

    @Override
    public Long countNewsByFieldCategory(String field) {
        return this.newsRepository.countByLinks_Category_FieldAndStatus(field,true);
    }
}
