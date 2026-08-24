package com.blogly.blogly.application.user.dto

import com.blogly.blogly.domain.user.Role

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: Role
)
