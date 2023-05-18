package com.turborvip.crawler.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "categories")
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

    public Category(String name, String description, String url, String create_by_id, String update_by_id) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Url> getUrl() {
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<Url>>() {
        }.getType();
        return gson.fromJson(this.url, objectType);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(String created_by_id) {
        this.created_by_id = created_by_id;
    }

    public String getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(String updated_by_id) {
        this.updated_by_id = updated_by_id;
    }

    public Set<News> getLikes() {
        return likes;
    }

    public void setLikes(Set<News> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", url='" + url + '\'' + ", created_at=" + created_at + ", updated_at=" + updated_at + ", published_at=" + published_at + ", created_by_id='" + created_by_id + '\'' + ", updated_by_id='" + updated_by_id + '\'' + ", likes=" + likes + '}';
    }
}
