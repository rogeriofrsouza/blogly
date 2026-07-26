package com.blogly.blogly.infrastructure.persistence.comment

import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<CommentEntity, Long> {

    fun findByPostIdOrderByCreatedAtAsc(postId: Long): List<CommentEntity>
}
