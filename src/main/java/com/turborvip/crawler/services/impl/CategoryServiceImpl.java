package com.turborvip.crawler.services.impl;

import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.repositories.CategoryRepository;
import com.turborvip.crawler.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryRepository.FieldNecessaryOfCategory> getAll() {
        return  this.categoryRepository.findByStatusTrue();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }
}
