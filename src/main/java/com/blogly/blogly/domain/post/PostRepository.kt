package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, PostId> {

    fun findByUserId(userId: UserId): List<Post>

    fun existsByTitleIgnoreCase(title: Title): Boolean
}
