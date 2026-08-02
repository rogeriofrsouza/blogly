package com.blogly.blogly.application.user.dto

import com.blogly.blogly.domain.user.User
import io.hypersistence.tsid.TSID

data class UserDetailsResponse(
    val id: String,
    val email: String,
    val role: String,
    val name: String
) {
    companion object {
        fun from(user: User) =
            UserDetailsResponse(
                TSID(user.id.value).toLowerCase(),
                user.email.value,
                user.role.name,
                user.name.value
            )
    }
}
