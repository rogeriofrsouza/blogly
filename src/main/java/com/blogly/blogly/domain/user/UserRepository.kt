package com.blogly.blogly.domain.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, UserId> {

    fun findByEmail(email: Email): User?

    fun existsByEmail(email: Email): Boolean
}
