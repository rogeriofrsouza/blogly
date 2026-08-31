package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.application.user.dto.toDetailsResponse
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class GetCurrentUserUseCase(
    private val repository: UserRepository,
    private val userProvider: UserProvider
) {
    fun execute(): UserDetailsResponse {
        val userId = userProvider.currentUserId()

        return repository
            .findById(userId)
            ?.toDetailsResponse()
            ?: throw UserNotFoundException(userId)
    }
}
