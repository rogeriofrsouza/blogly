package com.blogly.blogly.application.user.dto

data class UserDetailsResponse(
    val id: String,
    val email: String,
    val role: String,
    val name: String
)
