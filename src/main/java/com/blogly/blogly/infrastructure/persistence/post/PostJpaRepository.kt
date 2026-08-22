package com.blogly.blogly.infrastructure.persistence.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostEntity, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): PostEntity?

    fun findByUserIdAndDeletedAtIsNull(userId: Long): List<PostEntity>

    fun existsByTitleIgnoreCase(title: String): Boolean
}
