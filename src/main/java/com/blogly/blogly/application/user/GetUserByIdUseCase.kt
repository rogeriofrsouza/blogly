package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserNotFoundException
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(private val repository: UserRepository) {

    fun execute(id: UserId): UserDetailsResponse =
        repository.findById(id)
            ?.let { UserDetailsResponse.from(it) }
            ?: throw UserNotFoundException(id)
}
