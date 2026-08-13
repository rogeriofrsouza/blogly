package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostStatus
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.user.UserId
import org.springframework.data.repository.CrudRepository

interface PostJdbcRepository : CrudRepository<Post, PostId> {

    fun findByIdAndStatusNot(id: PostId, status: PostStatus): Post?

    fun findByUserIdAndStatusNot(userId: UserId, status: PostStatus): List<Post>

    fun existsByTitleIgnoreCase(title: Title): Boolean
}
