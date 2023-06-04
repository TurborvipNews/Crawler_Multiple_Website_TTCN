package com.turborvip.crawler.repositories;

import com.turborvip.crawler.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    NewsInfo findByCaptionOrUrl(String caption, String url);

    @Transactional
    @Modifying
    @Query("update News n set n.view_of_day = ?1")
    void updateView_of_dayBy(Long view_of_day);

    @Transactional
    @Modifying
    @Query("update News n set n.view_of_hour = ?1")
    void updateView_of_hourBy(Long view_of_hour);






}
