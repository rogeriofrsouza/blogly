package com.blogly.blogly.application.user.signup;

import com.blogly.blogly.application.user.UserDto;

public record SignUpResponse(String token, UserDto user) {
}
