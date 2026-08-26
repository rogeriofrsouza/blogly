package com.blogly.blogly.domain.user

import kotlin.time.Clock
import kotlin.time.Instant

class User(
    val id: UserId,
    val email: Email,
    val password: Password,
    var name: Name,
    var role: Role = Role.USER,
    var bio: Bio? = null,
    var avatarKey: AvatarKey? = null,
    val joinedAt: Instant = Clock.System.now()
) {
    fun promoteToAdmin() {
        role = Role.ADMIN
    }

    val isAdmin: Boolean
        get() = role == Role.ADMIN

    fun verifyPassword(plainText: String, hasher: PasswordHasher) = password.matches(plainText, hasher)

    fun updateProfile(name: Name, bio: Bio?) {
        this.name = name
        this.bio = bio
    }
}
