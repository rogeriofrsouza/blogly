package com.blogly.blogly.domain.user

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id val id: UserId,
    val email: Email,
    val password: Password,
    val name: Name,
    val role: Role = Role.USER,
    @Version val version: Int? = null
) {
    fun promoteToAdmin(): User = copy(role = Role.ADMIN)

    fun isAdmin(): Boolean = role == Role.ADMIN

    fun verifyPassword(plainText: String, hasher: PasswordHasher) = password.matches(plainText, hasher)
}
