package com.blogly.blogly.application.auth.dto;

import com.blogly.blogly.domain.user.Role;

public record SignUpResponse(Long id, String email, Role role) {
}
