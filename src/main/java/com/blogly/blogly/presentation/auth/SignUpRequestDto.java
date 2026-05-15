package com.blogly.blogly.presentation.auth;

import com.blogly.blogly.application.auth.dto.SignUpRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignUpRequestDto(
        @Email
        @NotBlank
        @Length(max = 50)
        String email,

        @NotBlank
        @Length(min = 8, max = 150)
        String password,

        @NotBlank
        @Length(max = 100)
        String name
) {
    public SignUpRequest toRequest() {
        return new SignUpRequest(email, password, name);
    }
}
