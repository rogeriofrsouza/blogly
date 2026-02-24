package com.blogly.blogly.application.dto;

public record CreatePostRequest(
        String title,
        String content
) {
}
