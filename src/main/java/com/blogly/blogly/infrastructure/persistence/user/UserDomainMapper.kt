package com.blogly.blogly.infrastructure.persistence.user

import com.blogly.blogly.domain.user.*
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant

object UserDomainMapper {

    fun toEntity(user: User): UserEntity =
        UserEntity().apply {
            id = user.id.value
            email = user.email.value
            password = user.password.hashedValue
            name = user.name.value
            role = user.role
            bio = user.bio?.value
            avatarKey = user.avatarKey?.value
            joinedAt = user.joinedAt.toJavaInstant()
        }

    fun toDomain(entity: UserEntity): User =
        User(
            UserId(entity.id),
            Email(entity.email),
            Password(entity.password),
            Name(entity.name),
            entity.role,
            entity.bio?.let(::Bio),
            entity.avatarKey?.let(::AvatarKey),
            entity.joinedAt.toKotlinInstant(),
        )
}
