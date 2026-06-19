package com.blogly.blogly.presentation.exception

import kotlin.time.Instant

data class ApiError(
    val timestamp: Instant,
    val status: Int,
    val message: String
)
