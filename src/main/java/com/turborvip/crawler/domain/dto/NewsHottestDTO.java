package com.turborvip.crawler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewsHottestDTO {
    private Long id;

    private String caption;

    private String thumbnail;

    private String description;

    private Long viewOfHour;
}
