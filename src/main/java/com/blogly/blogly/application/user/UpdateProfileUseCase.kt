package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.application.user.dto.UpdateProfileRequest
import com.blogly.blogly.domain.user.Bio
import com.blogly.blogly.domain.user.Name
import com.blogly.blogly.domain.user.UserRepository
import com.blogly.blogly.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UpdateProfileUseCase(
    private val repository: UserRepository,
    private val userProvider: UserProvider
) {
    @Transactional
    fun execute(request: UpdateProfileRequest) {
        val userId = userProvider.currentUserId()
        val user = repository.findById(userId) ?: throw UserNotFoundException(userId)

        user.updateProfile(
            Name(request.name), request.bio?.let(::Bio)
        )

        repository.save(user)
    }
}
