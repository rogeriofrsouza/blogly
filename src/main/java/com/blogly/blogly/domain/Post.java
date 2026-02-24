package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private PostId id;
    private Title title;
    private Content content;

    public Post(PostId id, Title title, Content content) {
        Objects.requireNonNull(id, "PostId cannot be null");
        this.id = id;
        this(title, content);
    }

    public Post(Title title, Content content) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;
    }
}
