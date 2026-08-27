package com.blogly.blogly.presentation.post;

import com.blogly.blogly.application.post.dto.CreatePostRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequestDto(
        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        @Size(max = 1000)
        String content
) {
    public CreatePostRequest toRequest() {
        return new CreatePostRequest(title, content);
    }
}
