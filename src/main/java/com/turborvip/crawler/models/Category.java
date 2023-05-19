package com.turborvip.crawler.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private String url;
    private static final boolean status = true;
    private Date created_at = new Date();
    private Date updated_at = new Date();
    private Date published_at = new Date();
    private String created_by_id;
    private String updated_by_id;

    @ManyToMany(mappedBy = "likedNews")
    @JsonBackReference
    private Set<News> likes;

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
}
