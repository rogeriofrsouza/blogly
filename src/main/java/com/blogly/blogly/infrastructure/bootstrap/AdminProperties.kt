package com.blogly.blogly.infrastructure.bootstrap

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.admin")
data class AdminProperties(
    val email: String,
    val password: String,
    val name: String
)
