package com.blogly.blogly.presentation.auth;

import com.blogly.blogly.application.auth.dto.SignUpRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
        @Email
        @NotBlank
        @Size(max = 50)
        String email,

        @NotBlank
        @Size(min = 8, max = 150)
        String password,

        @NotBlank
        @Size(max = 100)
        String name
) {
    public SignUpRequest toRequest() {
        return new SignUpRequest(email, password, name);
    }
}
