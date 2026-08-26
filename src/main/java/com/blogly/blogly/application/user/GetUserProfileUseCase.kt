package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserProfileResponse
import com.blogly.blogly.application.user.dto.toProfileResponse
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class GetUserProfileUseCase(
    private val repository: UserRepository
) {
    fun execute(userId: UserId): UserProfileResponse =
        repository.findById(userId)
            ?.let(User::toProfileResponse)
            ?: throw UserNotFoundException(userId)
}
