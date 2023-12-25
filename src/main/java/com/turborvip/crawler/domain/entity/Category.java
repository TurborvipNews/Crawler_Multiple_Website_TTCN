package com.turborvip.crawler.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.lang.reflect.Type;
import java.util.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String name;
    private String field;
    private String description;

    @Column(unique = true)
    private String url;
    private boolean status = true;

    @Column(name="created_at")
    private Date createdAt = new Date();

    @Column(name="updated_at")
    private Date updatedAt = new Date();

    @Column(name="published_at")
    private Date publishedAt = new Date();

    @Column(name="created_by_id")
    private String createdById;

    @Column(name="updated_by_id")
    private String updatedById;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JsonManagedReference
    private Set<NewsCategoriesLinks> links;

    @OneToMany(mappedBy = "categoryChild",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    private Set<CategoryCategoryLinks> linkParent;

    public Category(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public ArrayList<Url> getUrl() {
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<Url>>() {
        }.getType();
        return gson.fromJson(this.url, objectType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
