package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult

interface UserRepository {

    fun findById(id: UserId): User?

    fun findByEmail(email: Email): User?

    fun findAll(pageQuery: PageQuery): PageResult<User>

    fun existsByEmail(email: Email): Boolean

    fun save(user: User): UserId
}
