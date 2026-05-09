package com.blogly.blogly.application.user;

import com.blogly.blogly.domain.user.Role;
import com.blogly.blogly.domain.user.User;

public record UserDto(
        Long id,
        String email,
        String name,
        Role role
) {
    public static UserDto from(User user) {
        return new UserDto(
                user.getId().value(),
                user.getEmail().value(),
                user.getName().value(),
                user.getRole()
        );
    }
}
