package com.blogly.blogly.blog.application.dto;

public record CreateArticleRequest(
        String title,
        String body,
        String summary
) {
}
