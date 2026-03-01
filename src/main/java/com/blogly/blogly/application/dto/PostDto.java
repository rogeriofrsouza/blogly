package com.blogly.blogly.application.dto;

import com.blogly.blogly.domain.Post;
import com.blogly.blogly.domain.PostId;

public record PostDto(
        Long id,
        String title,
        String content
) {
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId().map(PostId::value).orElse(null),
                post.getTitle().value(),
                post.getContent().value()
        );
    }
}
