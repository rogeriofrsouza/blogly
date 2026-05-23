package com.blogly.blogly.application.auth.dto;

import com.blogly.blogly.domain.user.Role;

public record SignInResponse(String email, Role role, String token) {
}
