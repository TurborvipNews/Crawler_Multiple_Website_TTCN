package com.turborvip.crawler.domain.dto;

import com.turborvip.crawler.domain.entity.Category;
import com.turborvip.crawler.domain.entity.NewsCategoriesLinks;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Long id;

    private String name;

    private Date createdAt;

    private String field;

    private String description;
}
