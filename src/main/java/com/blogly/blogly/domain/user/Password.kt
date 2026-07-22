package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.user.exception.InvalidPasswordException

data class Password(val hashedValue: String) {

    fun matches(plainText: String, hasher: PasswordHasher) = hasher.matches(plainText, hashedValue)

    companion object {
        private const val MIN_LENGTH = 8

        fun create(plainText: String, hasher: PasswordHasher): Password {
            if (plainText.length < MIN_LENGTH) {
                throw InvalidPasswordException("Password must be at least $MIN_LENGTH characters")
            }

            return Password(hasher.hash(plainText))
        }
    }
}
