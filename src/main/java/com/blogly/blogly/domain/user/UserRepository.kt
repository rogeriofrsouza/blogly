package com.blogly.blogly.domain.user

interface UserRepository {

    fun findById(id: UserId): User?

    fun findByEmail(email: Email): User?

    fun existsByEmail(email: Email): Boolean

    fun save(user: User): UserId
}
