package com.blogly.blogly.domain.user

import io.hypersistence.tsid.TSID

class User(
    val id: UserId,
    val email: Email,
    val password: Password,
    val name: Name,
    var role: Role
) {
    fun promoteToAdmin() {
        role = Role.ADMIN
    }

    val isAdmin: Boolean
        get() = role == Role.ADMIN

    fun verifyPassword(plainText: String, hasher: PasswordHasher) = password.matches(plainText, hasher)

    companion object {
        @JvmStatic
        fun signUp(email: Email, password: Password, name: Name) =
            User(
                UserId(TSID.fast().toLong()),
                email,
                password,
                name,
                Role.USER
            )
    }
}
