package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private PostId id;
    private Title title;
    private Content content;
    private PostStatus status;

    public Post(PostId id, Title title, Content content, PostStatus status) {
        Objects.requireNonNull(id, "PostId cannot be null");
        this.id = id;
        this(title, content, status);
    }

    public Post(Title title, Content content, PostStatus status) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");

        this.title = title;
        this.content = content;
        this.status = status;
    }
}
