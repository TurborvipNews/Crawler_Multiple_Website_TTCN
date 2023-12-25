package com.turborvip.crawler.adapter.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface CategoriesResource {
    @GetMapping("/getAllCategories")
    ResponseEntity<?> getAllCategories(HttpServletRequest request);
}
