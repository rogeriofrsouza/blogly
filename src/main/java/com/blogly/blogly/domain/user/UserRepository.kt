package com.blogly.blogly.domain.user

import java.util.*

interface UserRepository {

    fun findById(id: UserId): Optional<User>

    fun findByEmail(email: Email): Optional<User>

    fun existsByEmail(email: Email): Boolean

    fun save(user: User): UserId
}
