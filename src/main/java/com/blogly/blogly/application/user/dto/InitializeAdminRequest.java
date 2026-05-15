package com.blogly.blogly.application.user.dto;

public record InitializeAdminRequest(
        String email,
        String password,
        String name
) {
}
