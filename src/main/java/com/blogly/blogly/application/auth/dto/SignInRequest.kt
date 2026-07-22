package com.blogly.blogly.application.auth.dto

data class SignInRequest(
    val email: String,
    val password: String
)
