package com.blogly.blogly.application.user.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.user.User
import java.time.Instant
import kotlin.time.toJavaInstant

data class UserDetailsResponse(
    val id: String,
    val email: String,
    val role: String,
    val name: String,
    val bio: String?,
    val avatarKey: String?,
    val joinedAt: Instant
)

fun User.toDetailsResponse() =
    UserDetailsResponse(
        id = TsidCodec.encode(id.value),
        email = email.value,
        role = role.name,
        name = name.value,
        bio = bio?.value,
        avatarKey = avatarKey?.value,
        joinedAt = joinedAt.toJavaInstant()
    )
