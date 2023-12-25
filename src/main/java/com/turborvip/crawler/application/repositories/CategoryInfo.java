package com.turborvip.crawler.application.repositories;


import com.turborvip.crawler.domain.entity.Category;
import com.turborvip.crawler.domain.entity.CategoryCategoryLinks;
import com.turborvip.crawler.domain.entity.Url;

import java.util.ArrayList;
import java.util.Set;

/**
 * Projection for {@link Category}
 */
public interface CategoryInfo {
    Long getId();

    String getName();

    String getField();

    ArrayList<Url> getUrl();

    Set<CategoriesCategoryParentInfo> getLinkParent();

    /**
     * Projection for {@link CategoryCategoryLinks}
     */
    interface CategoriesCategoryParentInfo {
        CategoryParentInfo getCategoryParent();

        /**
         * Projection for {@link Category}
         */
        interface CategoryParentInfo {
            Long getId();
        }
    }
}
