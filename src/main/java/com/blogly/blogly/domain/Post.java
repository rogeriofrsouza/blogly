package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private PostId id;
    private Title title;
    private Content content;
    private String summary;

    public Post(PostId id, Title title, Content content, String summary) {
        Objects.requireNonNull(id, "PostId cannot be null");
        this.id = id;
        this(title, content, summary);
    }

    public Post(Title title, Content content, String summary) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;

        if (summary != null && summary.length() > 100) {
            throw new IllegalArgumentException("Summary cannot exceed " + 100 + " characters");
        }

        this.summary = summary;
    }
}
