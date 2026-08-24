package com.blogly.blogly.infrastructure.persistence.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PostJpaRepository : JpaRepository<PostEntity, Long>, JpaSpecificationExecutor<PostEntity> {

    fun findByIdAndDeletedAtIsNull(id: Long): PostEntity?

    fun existsByTitleIgnoreCase(title: String): Boolean
}
