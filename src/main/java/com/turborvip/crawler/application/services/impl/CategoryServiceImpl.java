package com.turborvip.crawler.application.services.impl;

import com.turborvip.crawler.application.repositories.CategoryInfo;
import com.turborvip.crawler.application.services.CategoryService;
import com.turborvip.crawler.domain.dto.CategoryDTO;
import com.turborvip.crawler.domain.entity.Category;
import com.turborvip.crawler.application.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryInfo> getAll() {
        return  this.categoryRepository.findByStatus(true);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Optional<CategoryDTO> findByField(String field) {
        return this.categoryRepository.findByStatusAndField(true,field);
    }
}
