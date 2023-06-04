package com.turborvip.crawler.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    private String description;

    @Column(unique = true)
    private String url;
    private boolean status = true;
    private Date created_at = new Date();
    private Date updated_at = new Date();
    private Date published_at = new Date();
    private String created_by_id;
    private String updated_by_id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Set<NewsCategoriesLinks> links;

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
