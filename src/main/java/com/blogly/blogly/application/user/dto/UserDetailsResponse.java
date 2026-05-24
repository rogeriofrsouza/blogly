package com.blogly.blogly.application.user.dto;

import com.blogly.blogly.domain.user.User;

public record UserDetailsResponse(
        Long id,
        String email,
        String role,
        String name
) {
    public static UserDetailsResponse from(User user) {
        return new UserDetailsResponse(
                user.getId().value(),
                user.getEmail().value(),
                user.getRole().name(),
                user.getName().value()
        );
    }
}
