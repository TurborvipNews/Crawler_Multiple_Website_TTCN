package com.turborvip.crawler.repositories;


import com.turborvip.crawler.models.Category;
import com.turborvip.crawler.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    interface FieldNecessaryOfCategory {
        Long getId();
        String getName();

        String toString();

        ArrayList<Url> getUrl();
    }

    List<FieldNecessaryOfCategory> findAllBy();
}
