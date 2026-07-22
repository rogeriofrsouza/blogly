package com.blogly.blogly.application.user.dto

data class InitializeAdminRequest(
    val email: String,
    val password: String,
    val name: String
)
