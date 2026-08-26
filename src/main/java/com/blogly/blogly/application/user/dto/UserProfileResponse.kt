package com.blogly.blogly.application.user.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.user.User
import java.time.Instant
import kotlin.time.toJavaInstant

data class UserProfileResponse(
    val id: String,
    val name: String,
    val bio: String?,
    val avatarKey: String?,
    val joinedAt: Instant
)

fun User.toProfileResponse() =
    UserProfileResponse(
        id = TsidCodec.encode(id.value),
        name = name.value,
        bio = bio?.value,
        avatarKey = avatarKey?.value,
        joinedAt = joinedAt.toJavaInstant()
    )
