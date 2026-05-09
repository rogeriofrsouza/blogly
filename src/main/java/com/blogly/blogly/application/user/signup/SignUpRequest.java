package com.blogly.blogly.application.user.signup;

public record SignUpRequest(
        String email,
        String password,
        String name
) {
}
