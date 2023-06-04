package com.turborvip.crawler.repositories;

import java.util.ArrayList;
import java.util.Set;

/**
 * Projection for {@link com.turborvip.crawler.models.News}
 */
public interface NewsInfo {
    Long getId();

    Set<NewsCategoriesLinksInfo> getLinks();

    /**
     * Projection for {@link com.turborvip.crawler.models.NewsCategoriesLinks}
     */
    interface NewsCategoriesLinksInfo {
        CategoryInfo getCategory();

        /**
         * Projection for {@link com.turborvip.crawler.models.Category}
         */
        interface CategoryInfo {
            Long getId();
        }
    }
}