package com.blogly.blogly.blog.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateArticleDto(
        @NotBlank
        @Length(max = 255)
        String title,

        @NotBlank
        String body,

        @NotBlank
        @Length(max = 100)
        String summary
) {
}
