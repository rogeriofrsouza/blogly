package com.blogly.blogly.application.auth

import com.blogly.blogly.application.auth.dto.SignInRequest
import com.blogly.blogly.application.auth.dto.SignInResponse
import com.blogly.blogly.application.shared.IdProvider
import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.PasswordHasher
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class SignInUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher,
    private val tokenProvider: TokenProvider,
    private val idProvider: IdProvider
) {
    fun execute(request: SignInRequest): SignInResponse {
        val email = Email(request.email)

        val user = userRepository.findByEmail(email)
            ?: throw InvalidCredentialsException()

        if (!user.verifyPassword(request.password, passwordHasher)) {
            throw InvalidCredentialsException()
        }

        val token = tokenProvider.generateToken(user)

        return SignInResponse(
            idProvider.encode(user.id.value),
            user.email.value,
            user.role,
            token
        )
    }
}
