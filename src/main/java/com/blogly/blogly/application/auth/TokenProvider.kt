package com.blogly.blogly.application.auth

import com.blogly.blogly.domain.user.User

interface TokenProvider {

    fun generateToken(user: User): String

    fun extractUsername(token: String): String

    fun isTokenValid(token: String, username: String): Boolean
}
