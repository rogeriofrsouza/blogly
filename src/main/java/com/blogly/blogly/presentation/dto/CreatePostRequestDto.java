package com.blogly.blogly.presentation.dto;

import com.blogly.blogly.application.dto.CreatePostRequest;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreatePostRequestDto(
        @NotBlank
        @Length(max = 255)
        String title,

        @NotBlank
        String content
) {
    public CreatePostRequest toRequest() {
        return new CreatePostRequest(title, content);
    }
}
