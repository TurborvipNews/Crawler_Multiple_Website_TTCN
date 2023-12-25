package com.turborvip.crawler.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories_category_parent_links")
@Getter
@Setter
@NoArgsConstructor
public class CategoryCategoryLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryChild;

    @ManyToOne
    @JoinColumn(name = "inv_category_id")
    private Category categoryParent;
}
