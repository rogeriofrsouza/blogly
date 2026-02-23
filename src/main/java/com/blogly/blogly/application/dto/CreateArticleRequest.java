package com.blogly.blogly.application.dto;

public record CreateArticleRequest(
        String title,
        String body,
        String summary
) {
}
