package com.blogly.blogly.application.user.signup;

import com.blogly.blogly.application.dto.UserDto;

public record SignUpResponse(String token, UserDto user) {
}
