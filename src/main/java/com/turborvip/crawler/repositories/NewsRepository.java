package com.turborvip.crawler.repositories;

import com.turborvip.crawler.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    public News findByCaptionAndUrl(String caption,String url);

}
