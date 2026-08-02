package com.blogly.blogly.application.auth

import com.blogly.blogly.application.auth.dto.SignInRequest
import com.blogly.blogly.application.auth.dto.SignInResponse
import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.PasswordHasher
import com.blogly.blogly.domain.user.UserRepository
import io.hypersistence.tsid.TSID
import org.springframework.stereotype.Component

@Component
class SignInUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher,
    private val tokenProvider: TokenProvider
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
            TSID(user.id.value).toLowerCase(),
            user.email.value,
            user.role,
            token
        )
    }
}
