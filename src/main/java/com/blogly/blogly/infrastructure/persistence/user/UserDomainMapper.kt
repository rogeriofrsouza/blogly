package com.blogly.blogly.infrastructure.persistence.user

import com.blogly.blogly.domain.user.*
import org.springframework.stereotype.Component

@Component
class UserDomainMapper {

    fun toEntity(user: User): UserEntity =
        UserEntity().apply {
            id = user.id.value
            email = user.email.value
            password = user.password.hashedValue
            name = user.name.value
            role = user.role
        }

    fun toDomain(entity: UserEntity): User =
        User(
            UserId(entity.id),
            Email(entity.email),
            Password(entity.password),
            Name(entity.name),
            entity.role,
        )
}
