package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private PostId id;
    private String title;
    private String body;
    private String summary;

    public Post(PostId id, String title, String body, String summary) {
        Objects.requireNonNull(id, "PostId cannot be null");
        this.id = id;
        this(title, body, summary);
    }

    public Post(String title, String body, String summary) {
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
