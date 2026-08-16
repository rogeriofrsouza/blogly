package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.user.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PostJdbcRepository : CrudRepository<Post, PostId> {

    fun findByIdAndDeletedAtIsNull(id: PostId): Post?

    fun findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(userId: UserId, pageable: Pageable): Page<Post>

    fun existsByTitleIgnoreCase(title: Title): Boolean
}
