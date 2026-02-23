package com.blogly.blogly.presentation.dto;

import com.blogly.blogly.application.dto.CreateArticleRequest;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateArticleRequestDto(
        @NotBlank
        @Length(max = 255)
        String title,

        @NotBlank
        String body,

        @NotBlank
        @Length(max = 100)
        String summary
) {
    public CreateArticleRequest toRequest() {
        return new CreateArticleRequest(title, body, summary);
    }
}
