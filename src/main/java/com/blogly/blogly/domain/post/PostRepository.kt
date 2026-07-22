package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId

interface PostRepository {

    fun findById(id: PostId): Post?

    fun findByUserId(userId: UserId): List<Post>

    fun save(post: Post): PostId

    fun existsByTitle(title: Title): Boolean
}
