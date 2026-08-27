package com.blogly.blogly.presentation.me

import com.blogly.blogly.application.user.dto.UpdateProfileRequest
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateProfileDto(
    @field:NotBlank
    @field:Size(min = 3, max = 50)
    val name: String,

    @field:Size(max = 200)
    val bio: String?,
) {
    fun toRequest() = UpdateProfileRequest(name, bio)
}
