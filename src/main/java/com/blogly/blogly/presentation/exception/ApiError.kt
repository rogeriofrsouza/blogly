package com.blogly.blogly.presentation.exception

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.Instant

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiError(
    val timestamp: Instant,
    val status: Int,
    val message: String,
    val errors: List<String>? = null
)
