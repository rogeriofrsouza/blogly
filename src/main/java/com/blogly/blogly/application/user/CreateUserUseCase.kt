package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.IdGenerator
import com.blogly.blogly.application.user.dto.CreateUserRequest
import com.blogly.blogly.domain.user.*
import com.blogly.blogly.domain.user.exception.EmailAlreadyExistsException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateUserUseCase(
    private val repository: UserRepository,
    private val adminAccessGuard: AdminAccessGuard,
    private val passwordHasher: PasswordHasher,
    private val idGenerator: IdGenerator
) {
    @Transactional
    fun execute(request: CreateUserRequest): UserId {
        adminAccessGuard.requireAdmin()

        val email = Email(request.email)

        if (repository.existsByEmail(email)) {
            throw EmailAlreadyExistsException(email.value)
        }

        val user = User(
            id = UserId(idGenerator.generate()),
            email = email,
            password = Password.create(request.password, passwordHasher),
            name = Name(request.name)
        )

        return repository.save(user)
    }
}
