package com.turborvip.crawler.application.repositories;


import com.turborvip.crawler.domain.dto.CategoryDTO;
import com.turborvip.crawler.domain.entity.Category;
import com.turborvip.crawler.domain.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<CategoryInfo> findByStatus(boolean status);

    Optional<CategoryDTO> findByStatusAndField(boolean status, String field);

    interface FieldNecessaryOfCategory {
        Long getId();
        String getName();

        String toString();

        ArrayList<Url> getUrl();
    }

}

