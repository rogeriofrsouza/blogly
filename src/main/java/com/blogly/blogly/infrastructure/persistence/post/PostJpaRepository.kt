package com.blogly.blogly.infrastructure.persistence.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostEntity, Long> {

    fun existsByTitleIgnoreCase(title: String): Boolean
}
