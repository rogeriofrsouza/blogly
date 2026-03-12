package com.blogly.blogly.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Post {

    private final PostId id;
    private Title title;
    private Content content;
    private PostStatus status;

    public Post(PostId id, Title title, Content content, PostStatus status) {
        Objects.requireNonNull(id, "PostId cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");

        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public static Post create(Title title, Content content) {
        return new Post(
                new PostId(Long.MAX_VALUE),
                title,
                content,
                PostStatus.DRAFT);
    }
}
