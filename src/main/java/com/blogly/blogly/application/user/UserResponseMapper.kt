package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.IdProvider
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserResponseMapper(
    private val idProvider: IdProvider
) {
    fun toDetailsResponse(user: User) =
        UserDetailsResponse(
            id = idProvider.encode(user.id.value),
            email = user.email.value,
            role = user.role.name,
            name = user.name.value
        )
}
