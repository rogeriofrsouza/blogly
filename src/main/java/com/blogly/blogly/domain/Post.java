package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private PostId id;
    private Title title;
    private String body;
    private String summary;

    public Post(PostId id, Title title, String body, String summary) {
        Objects.requireNonNull(id, "PostId cannot be null");
        this.id = id;
        this(title, body, summary);
    }

    public Post(Title title, String body, String summary) {
        Objects.requireNonNull(title, "Title cannot be null");
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
