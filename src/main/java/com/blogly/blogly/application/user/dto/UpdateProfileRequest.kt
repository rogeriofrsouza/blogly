package com.blogly.blogly.application.user.dto

data class UpdateProfileRequest(
    val name: String,
    val bio: String?
)
