package com.turborvip.crawler.services;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryRepository.FieldNecessaryOfCategory> getAll();

    Optional<Category> findById(Long id);
}
