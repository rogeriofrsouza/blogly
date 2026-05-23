package com.blogly.blogly.presentation.auth;

import com.blogly.blogly.application.auth.dto.SignInRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInRequestDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8)
        String password
) {
    public SignInRequest toRequest() {
        return new SignInRequest(email, password);
    }
}
