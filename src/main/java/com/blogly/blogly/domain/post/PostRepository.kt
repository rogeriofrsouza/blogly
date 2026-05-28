package com.blogly.blogly.domain.post

interface PostRepository {

    fun findById(id: PostId): Post?

    fun save(post: Post): PostId

    fun existsByTitle(title: Title): Boolean
}
