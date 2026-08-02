package com.blogly.blogly.application.auth

import com.blogly.blogly.application.auth.dto.SignUpRequest
import com.blogly.blogly.application.auth.dto.SignUpResponse
import com.blogly.blogly.domain.user.*
import com.blogly.blogly.domain.user.exception.EmailAlreadyExistsException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SignUpUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    @Transactional
    fun execute(request: SignUpRequest): SignUpResponse {
        val email = Email(request.email)

        if (userRepository.existsByEmail(email)) {
            throw EmailAlreadyExistsException(email.value)
        }

        val user = User(
            email = email,
            password = Password.create(request.password, passwordHasher),
            name = Name(request.name)
        )

        userRepository.save(user)

        return SignUpResponse(
            user.id.value.toString(),
            user.email.value,
            user.role
        )
    }
}
