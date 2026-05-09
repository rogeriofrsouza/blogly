package com.blogly.blogly.application.post;

import com.blogly.blogly.domain.post.Post;

public record PostDto(
        Long id,
        String title,
        String content
) {
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId().value(),
                post.getTitle().value(),
                post.getContent().value()
        );
    }
}
