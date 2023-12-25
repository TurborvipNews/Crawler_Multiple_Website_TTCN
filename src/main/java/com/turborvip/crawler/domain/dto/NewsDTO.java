package com.turborvip.crawler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewsDTO {
    private Long id;

    private String caption;

    private String thumbnail;

    private String description;

    private String content;

    private String author;

    private Long viewOfDay;

    private Long viewOfHour;

    private String url;

    private boolean status;

//    private Date created_at;
//
//    private Date updated_at;
//
//    private Date published_at;
//
//    private String created_by_id;
//
//    private String updated_by_id;

}
