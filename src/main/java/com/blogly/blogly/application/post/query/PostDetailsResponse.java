package com.blogly.blogly.application.post.query;

import com.blogly.blogly.domain.post.Post;

public record PostDetailsResponse(
        Long id,
        String title,
        String content
) {
    public static PostDetailsResponse from(Post post) {
        return new PostDetailsResponse(
                post.getId().value(),
                post.getTitle().value(),
                post.getContent().value()
        );
    }
}
