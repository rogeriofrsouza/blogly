package com.blogly.blogly.application.auth.dto;

public record SignUpRequest(
        String email,
        String password,
        String name
) {
}
