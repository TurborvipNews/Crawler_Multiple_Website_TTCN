package com.turborvip.crawler.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "news")
public class News {

    private static final Date TIME_NOW = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String caption;
    private String thumbnail;

    private String description;

    private String content;

    private String author;

    private Long view_of_day = 0L;

    private Long view_of_hour = 0L;

    private String url;

    private boolean status = true;

    private Long categoryId;

    private Date created_at = TIME_NOW;
    private Date updated_at = TIME_NOW;
    private Date published_at = TIME_NOW;
    private String created_by_id;
    private String updated_by_id;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "news_categories_links",
            joinColumns = @JoinColumn(name = "new_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private Set<Category> likedNews;

    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getView_of_day() {
        return view_of_day;
    }

    public void setView_of_day(Long view_of_day) {
        this.view_of_day = view_of_day;
    }

    public Long getView_of_hour() {
        return view_of_hour;
    }

    public void setView_of_hour(Long view_of_hour) {
        this.view_of_hour = view_of_hour;
    }

    public String getCreate_by_id() {
        return created_by_id;
    }

    public void setCreate_by_id(String create_by_id) {
        this.created_by_id = create_by_id;
    }

    public String getUpdate_by_id() {
        return updated_by_id;
    }

    public void setUpdate_by_id(String update_by_id) {
        this.updated_by_id = update_by_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Category> getLikedNews() {
        return likedNews;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long category_id) {
        this.categoryId = category_id;
    }

    public void setLikedNews(Set<Category> likedNews) {
        this.likedNews = likedNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "caption='" + caption + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", view_of_day=" + view_of_day +
                ", view_of_hour=" + view_of_hour +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
