package com.blogly.blogly.application.user.signup;

import com.blogly.blogly.domain.user.Role;

public record SignUpResponse(String email, Role role) {
}
