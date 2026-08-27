package com.blogly.blogly.application.user.dto

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String
)
