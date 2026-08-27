package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.application.user.dto.toDetailsResponse
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class GetUserDetailsUseCase(
    private val repository: UserRepository,
    private val adminAccessGuard: AdminAccessGuard
) {
    fun execute(userId: UserId): UserDetailsResponse {
        adminAccessGuard.requireAdmin()

        return repository.findById(userId)
            ?.toDetailsResponse()
            ?: throw UserNotFoundException(userId)
    }
}
