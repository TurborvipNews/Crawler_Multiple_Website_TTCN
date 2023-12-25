package com.turborvip.crawler.application.repositories;

import com.turborvip.crawler.domain.dto.NewsDTO;
import com.turborvip.crawler.domain.dto.NewsDetailDTO;
import com.turborvip.crawler.domain.dto.NewsHottestDTO;
import com.turborvip.crawler.domain.dto.NewsNewestDTO;
import com.turborvip.crawler.domain.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    NewsInfo findByCaptionOrUrl(String caption, String url);

    @Transactional
    @Modifying
    @Query("update News n set n.viewOfDay = ?1")
    void updateView_of_dayBy(Long view_of_day);

    @Transactional
    @Modifying
    @Query("update News n set n.viewOfHour = ?1")
    void updateView_of_hourBy(Long view_of_hour);

    List<NewsDTO> findByStatus(boolean status, Pageable pageable);

    List<NewsNewestDTO> findByStatusOrderByCreatedAtDesc(boolean status, Pageable pageable);

    List<NewsHottestDTO> findByStatusOrderByViewOfHourDesc(boolean status, Pageable pageable);

    Optional<NewsDetailDTO> findNewsDetailDTOByStatusAndId(boolean status, Long id);

    List<NewsNewestDTO> findByLinks_Category_FieldAndStatus(String field, boolean status, Pageable pageable);

    long countByLinks_Category_FieldAndStatus(String field, boolean status);

}
