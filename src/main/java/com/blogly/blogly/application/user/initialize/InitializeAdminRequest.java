package com.blogly.blogly.application.user.initialize;

public record InitializeAdminRequest(
        String email,
        String password,
        String name
) {
}
