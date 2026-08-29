package com.blogly.blogly.application.auth

import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.Role
import com.blogly.blogly.domain.user.UserId

data class TokenClaims(
    val email: Email,
    val role: Role,
    val userId: UserId,
)
