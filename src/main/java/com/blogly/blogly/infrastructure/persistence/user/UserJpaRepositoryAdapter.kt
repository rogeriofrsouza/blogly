package com.blogly.blogly.infrastructure.persistence.user

import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

    override fun findAll(pageQuery: PageQuery): PageResult<User> {
        val pageable = PageRequest.of(pageQuery.page, pageQuery.size, Sort.by(Sort.Direction.DESC, "id"))
        val users = repository.findAll(pageable)

        return PageResult(
            users.content.map(UserDomainMapper::toDomain),
            pageQuery.page,
            pageQuery.size,
            users.totalElements
        )
    }

    override fun existsByEmail(email: Email): Boolean =
        repository.existsByEmail(email.value)

    override fun save(user: User): UserId {
        val entity = UserDomainMapper.toEntity(user)
        repository.save(entity)
        return user.id
    }
}
