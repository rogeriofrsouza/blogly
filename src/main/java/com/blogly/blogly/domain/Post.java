package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
public class Post {

    private PostId id;
    private Title title;
    private Content content;
    private PostStatus status;

    public Post(Title title, Content content, PostStatus status) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");

        this.title = title;
        this.content = content;
        this.status = status;
    }

    public void setId(PostId id) {
        Objects.requireNonNull(id, "PostId cannot be null");

        if (this.id != null) {
            throw new IllegalStateException("PostId already exists");
        }

        this.id = id;
    }

    public Optional<PostId> getId() {
        return Optional.ofNullable(id);
    }
}
