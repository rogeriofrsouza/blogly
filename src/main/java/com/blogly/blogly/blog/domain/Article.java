package com.blogly.blogly.blog.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Article {

    private ArticleId id;
    private String title;
    private String body;
    private String summary;

    public Article(ArticleId id, String title, String body, String summary) {
        Objects.requireNonNull(id, "ArticleId cannot be null");
        this.id = id;
        this(title, body, summary);
    }

    public Article(String title, String body, String summary) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (title.length() > 255) {
            throw new IllegalArgumentException("Title cannot exceed 255 characters");
        }

        this.title = title;

        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException("Body cannot be null or blank");
        }

        this.body = body;

        if (summary != null && summary.length() > 100) {
            throw new IllegalArgumentException("Summary cannot exceed " + 100 + " characters");
        }

        this.summary = summary;
    }
}
