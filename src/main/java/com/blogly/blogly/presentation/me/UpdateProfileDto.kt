package com.blogly.blogly.presentation.me

import com.blogly.blogly.application.user.dto.UpdateProfileRequest
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UpdateProfileDto(
    @field:NotBlank
    @field:Length(min = 3, max = 50)
    val name: String,

    @field:Length(max = 200)
    val bio: String?,
) {
    fun toRequest() = UpdateProfileRequest(name, bio)
}
