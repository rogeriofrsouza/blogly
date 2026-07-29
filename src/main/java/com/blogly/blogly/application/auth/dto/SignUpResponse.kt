package com.blogly.blogly.application.auth.dto

import com.blogly.blogly.domain.user.Role

data class SignUpResponse(
    val id: String,
    val email: String,
    val role: Role
)
