package com.turborvip.crawler.services;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.repositories.CategoryRepository;

import java.util.List;

public interface CategoryService {
    public List<CategoryRepository.FieldNecessaryOfCategory> getAll();
}
