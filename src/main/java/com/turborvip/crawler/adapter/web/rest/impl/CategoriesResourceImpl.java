package com.turborvip.crawler.adapter.web.rest.impl;

import com.turborvip.crawler.adapter.web.base.RestApiV1;
import com.turborvip.crawler.adapter.web.base.VsResponseUtil;
import com.turborvip.crawler.adapter.web.rest.CategoriesResource;
import com.turborvip.crawler.application.repositories.CategoryInfo;
import com.turborvip.crawler.application.services.CategoryService;
import com.turborvip.crawler.domain.dto.CategoryDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestApiV1
public class CategoriesResourceImpl implements CategoriesResource {

    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseEntity<?> getAllCategories(HttpServletRequest request) {
        try {
            List<CategoryInfo> data = categoryService.getAll();
            return VsResponseUtil.ok("success",data);
        } catch (Exception e) {
            return VsResponseUtil.error(HttpStatus.valueOf(500), e.getMessage());
        }
    }
}
