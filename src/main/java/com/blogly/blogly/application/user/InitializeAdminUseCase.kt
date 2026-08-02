package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.InitializeAdminRequest
import com.blogly.blogly.domain.user.*
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitializeAdminUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    @Transactional
    fun execute(request: InitializeAdminRequest) {
        val email = Email(request.email)

        val user = userRepository.findByEmail(email)
            ?: User(
                email = email,
                password = Password.create(request.password, passwordHasher),
                name = Name(request.name)
            )

        if (user.isAdmin) {
            throw UserAlreadyAdminException()
        }

        user.promoteToAdmin()
        userRepository.save(user)
    }
}
