package com.blogly.blogly.application.user.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.user.User

data class UserDetailsResponse(
    val id: String,
    val email: String,
    val role: String,
    val name: String
)

fun User.toDetailsResponse() =
    UserDetailsResponse(
        id = TsidCodec.encode(id.value),
        email = email.value,
        role = role.name,
        name = name.value
    )
