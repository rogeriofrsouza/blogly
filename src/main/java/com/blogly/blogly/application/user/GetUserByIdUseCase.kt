package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.application.user.dto.toDetailsResponse
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(
    private val repository: UserRepository
) {
    fun execute(userId: UserId): UserDetailsResponse =
        repository.findById(userId)
            ?.let(User::toDetailsResponse)
            ?: throw UserNotFoundException(userId)
}
