package com.blogly.blogly.application.dto;

public record InitializeAdminRequest(
        String email,
        String password,
        String name
) {
}
