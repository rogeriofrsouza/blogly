package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UpdateCommentDto(
    @field:NotBlank
    @field:Length(max = 200)
    val body: String
) {
    fun toRequest() = UpdateCommentRequest(body)
}
