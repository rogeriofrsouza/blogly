package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateCommentDto(
    @field:NotBlank
    @field:Size(max = 200)
    val body: String
) {
    fun toRequest() = UpdateCommentRequest(body)
}
