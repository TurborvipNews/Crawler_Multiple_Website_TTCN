package com.turborvip.crawler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class NewsDetailDTO {
    private Long id;
    private String author;
    private String caption;
    private String content;
    private Date createdAt;
    private String description;
    private Date publishedAt;
    private boolean status;
    private String thumbnail;
    private Date updatedAt;
    private String url;
    private Long viewOfDay;
    private Long viewOfHour;
}
