package com.blogly.blogly.application.post.dto;

public record CreatePostRequest(
        String title,
        String content
) {
}
