package com.blogly.blogly.presentation.admin;

import com.blogly.blogly.application.user.dto.CreateUserRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank
        @Email
        @Size(max = 50)
        String email,

        @NotBlank
        @Size(min = 8, max = 150)
        String password,

        @NotBlank
        @Size(min = 3, max = 100)
        String name
) {
    public CreateUserRequest toRequest() {
        return new CreateUserRequest(email, password, name);
    }
}
