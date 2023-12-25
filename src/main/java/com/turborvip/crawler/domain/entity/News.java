package com.turborvip.crawler.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Column(name = "view_of_day")
    private Long viewOfDay = 0L;

    @Column(name = "view_of_hour")
    private Long viewOfHour = 0L;

    @Column(unique = true)
    private String url;

    private boolean status = true;

    @Column(name = "created_at")
    private Date createdAt = TIME_NOW;

    @Column(name = "updated_at")
    private Date updatedAt = TIME_NOW;

    @Column(name = "published_at")
    private Date publishedAt = TIME_NOW;

    @Column(name = "created_by_id")
    private String createdById;

    @Column(name = "updated_by_id")
    private String updatedById;

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
