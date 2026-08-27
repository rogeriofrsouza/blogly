package com.blogly.blogly.presentation.post;

import com.blogly.blogly.application.post.dto.UpdatePostRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePostRequestDto(
        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        @Size(max = 1000)
        String content
) {
    public UpdatePostRequest toRequest() {
        return new UpdatePostRequest(title, content);
    }
}
