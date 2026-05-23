package com.blogly.blogly.application.auth.dto;

import com.blogly.blogly.domain.user.Role;

public record SignInResponse(
        Long id,
        String email,
        Role role,
        String token
) {
}
