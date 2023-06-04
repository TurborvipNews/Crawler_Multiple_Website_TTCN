package com.turborvip.crawler.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
public class News {
    private static final Date TIME_NOW = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String caption;

    @NotBlank(message = "Thumbnail must not blank!")
    @NotNull(message = "Thumbnail must not null!")
    private String thumbnail;

    private String description;

    @NotBlank(message = "Content must not blank!")
    @NotNull(message = "Content must not null!")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news", cascade = CascadeType.ALL)
    private Set<NewsCategoriesLinks> links;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        News news = (News) o;
        return getId() != null && Objects.equals(getId(), news.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
