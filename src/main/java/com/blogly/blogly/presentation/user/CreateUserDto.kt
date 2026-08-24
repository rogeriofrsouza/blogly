package com.blogly.blogly.presentation.user

import com.blogly.blogly.application.user.dto.CreateUserRequest
import com.blogly.blogly.domain.user.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class CreateUserDto(
    @field:NotBlank
    @field:Email
    @field:Length(max = 50)
    val email: String,

    @field:NotBlank
    @field:Length(min = 8, max = 150)
    val password: String,

    @field:NotBlank
    @field:Length(min = 3, max = 100)
    val name: String,

    val role: Role
) {
    fun toRequest() = CreateUserRequest(email, password, name, role)
}
