package com.blogly.blogly.application.user.dto

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
                user.id.value.toString(),
                user.email.value,
                user.role.name,
                user.name.value
            )
    }
}
