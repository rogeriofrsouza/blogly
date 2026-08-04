package com.blogly.blogly.domain.user

class User(
    val id: UserId,
    val email: Email,
    val password: Password,
    val name: Name,
    var role: Role = Role.USER
) {
    fun promoteToAdmin() {
        role = Role.ADMIN
    }

    val isAdmin: Boolean
        get() = role == Role.ADMIN

    fun verifyPassword(plainText: String, hasher: PasswordHasher) = password.matches(plainText, hasher)
}
