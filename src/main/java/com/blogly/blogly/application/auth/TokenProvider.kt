package com.blogly.blogly.application.auth

import com.blogly.blogly.domain.user.User

interface TokenProvider {

    fun generateToken(user: User): String

    fun parseToken(token: String): TokenClaims?
}
