package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserResponseMapper {

    fun toDetailsResponse(user: User) =
        UserDetailsResponse(
            id = TsidCodec.encode(user.id.value),
            email = user.email.value,
            role = user.role.name,
            name = user.name.value
        )
}
