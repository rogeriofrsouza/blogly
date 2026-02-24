package com.blogly.blogly.application.dto;

import com.blogly.blogly.domain.Post;

public record PostDto(
        Long id,
        String title,
        String content,
        String summary
) {
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId().value(),
                post.getTitle().value(),
                post.getContent().value(),
                post.getSummary()
        );
    }
}
