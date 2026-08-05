package com.blogly.blogly.application.user.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.user.User

data class UserDetailsResponse(
    val id: String,
    val email: String,
    val role: String,
    val name: String
) {
    companion object {
        fun from(user: User) =
            UserDetailsResponse(
                id = TsidCodec.encode(user.id.value),
                email = user.email.value,
                role = user.role.name,
                name = user.name.value
            )
    }
}
