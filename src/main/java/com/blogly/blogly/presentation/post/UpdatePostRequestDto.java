package com.blogly.blogly.presentation.post;

import com.blogly.blogly.application.post.dto.UpdatePostRequest;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdatePostRequestDto(
        @NotBlank
        @Length(max = 255)
        String title,

        @NotBlank
        @Length(max = 1000)
        String content
) {
    public UpdatePostRequest toRequest() {
        return new UpdatePostRequest(title, content);
    }
}
