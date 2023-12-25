package com.turborvip.crawler.application.https.response;

import com.turborvip.crawler.domain.dto.CategoryDTO;
import com.turborvip.crawler.domain.dto.NewsNewestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsByCategoryResponse {

    private List<NewsNewestDTO> listNews;

    private CategoryDTO category;

    private int totalPage;
}
