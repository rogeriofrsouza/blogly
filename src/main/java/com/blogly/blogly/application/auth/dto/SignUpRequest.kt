package com.blogly.blogly.application.auth.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String
)
