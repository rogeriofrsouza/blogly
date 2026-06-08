package com.blogly.blogly.domain.user

interface PasswordHasher {

    fun hash(plainText: String): String

    fun matches(plainText: String, hashedValue: String): Boolean
}
