package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(
    private val repository: UserRepository
) {
    fun execute(userId: UserId): UserDetailsResponse =
        repository.findByIdOrNull(userId)
            ?.let(UserDetailsResponse::from)
            ?: throw UserNotFoundException(userId)
}
