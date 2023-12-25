package com.turborvip.crawler.application.repositories;

import com.turborvip.crawler.domain.entity.Category;
import com.turborvip.crawler.domain.entity.News;
import com.turborvip.crawler.domain.entity.NewsCategoriesLinks;

import java.util.Set;

/**
 * Projection for {@link News}
 */
public interface NewsInfo {
    Long getId();

    Set<NewsCategoriesLinksInfo> getLinks();

    /**
     * Projection for {@link NewsCategoriesLinks}
     */
    interface NewsCategoriesLinksInfo {
        CategoryInfo getCategory();

        /**
         * Projection for {@link Category}
         */
        interface CategoryInfo {
            Long getId();
        }
    }
}