package com.turborvip.crawler.application.services;

import com.turborvip.crawler.application.repositories.CategoryInfo;
import com.turborvip.crawler.domain.dto.CategoryDTO;
import com.turborvip.crawler.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryInfo> getAll();

    Optional<Category> findById(Long id);

    Optional<CategoryDTO> findByField(String field);
}
