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
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getRole().name(),
                user.getName().getValue()
        );
    }
}
