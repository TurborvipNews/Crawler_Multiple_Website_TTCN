package com.turborvip.crawler.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@ToString
public class News {
    private static final Date TIME_NOW = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String caption;

    @NotBlank(message = "Thumbnail must not blank!")
    private String thumbnail;

    private String description;

    @NotBlank(message = "Content must not blank!")
    private String content;

    private String author;

    private Long view_of_day = 0L;

    private Long view_of_hour = 0L;

    @Column(unique = true)
    private String url;

    private boolean status = true;

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

}
