package com.blogly.blogly.infrastructure.persistence.user

import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserJpaRepositoryAdapter(
    private val repository: UserJpaRepository
) : UserRepository {

    override fun findById(id: UserId): User? =
        repository.findByIdOrNull(id.value)
            ?.let { UserDomainMapper.toDomain(it) }

    override fun findByEmail(email: Email): User? =
        repository.findByEmail(email.value)
            ?.let { UserDomainMapper.toDomain(it) }

    override fun existsByEmail(email: Email): Boolean =
        repository.existsByEmail(email.value)

    override fun save(user: User): UserId {
        val entity = UserDomainMapper.toEntity(user)
        repository.save(entity)
        return user.id
    }
}
