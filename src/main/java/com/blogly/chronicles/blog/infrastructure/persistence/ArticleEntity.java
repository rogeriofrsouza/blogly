package com.blogly.chronicles.blog.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_article_title", columnNames = {"title"})
        })
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @Column(length = 100)
    private String summary;
}
