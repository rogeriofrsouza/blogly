package com.blogly.chronicles.article.domain;

import lombok.Getter;

@Getter
public class Article {

    private static final int MAX_SUMMARY_LENGTH = 100;

    private final ArticleId id;
    private String title;
    private String body;
    private String summary;

    public Article(ArticleId id, String title, String body, String summary) {
        this.id = id;
        setTitle(title);
        setBody(body);
        setSummary(summary);
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }

        if (title.length() > 255) {
            throw new IllegalArgumentException("Title cannot exceed 255 characters");
        }

        this.title = title;
    }

    public void setBody(String body) {
        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException("Body cannot be null or blank");
        }

        this.body = body;
    }

    public void setSummary(String summary) {
        if (summary != null && summary.length() > MAX_SUMMARY_LENGTH) {
            throw new IllegalArgumentException("Summary cannot exceed " + MAX_SUMMARY_LENGTH + " characters");
        }

        this.summary = summary;
    }
}
