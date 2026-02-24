package com.blogly.blogly.presentation.dto;

import com.blogly.blogly.application.dto.CreatePostRequest;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreatePostRequestDto(
        @NotBlank
        @Length(max = 255)
        String title,

        @NotBlank
        String content,

        @NotBlank
        @Length(max = 100)
        String summary
) {
    public CreatePostRequest toRequest() {
        return new CreatePostRequest(title, content, summary);
    }
}
