package com.turborvip.crawler.application.https.response;

import com.turborvip.crawler.domain.dto.NewsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    private List<NewsDTO> news = null;
}
