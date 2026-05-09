package com.blogly.blogly.application.dto;

public record SignUpRequest(
        String email,
        String password,
        String name
) {
}
