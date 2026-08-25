package com.blogly.blogly.infrastructure.persistence.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface PostJpaRepository : JpaRepository<PostEntity, Long>, JpaSpecificationExecutor<PostEntity> {

    fun findByIdAndDeletedAtIsNull(id: Long): PostEntity?

    fun existsByTitleIgnoreCase(title: String): Boolean

    @Query(
        value = """
        select p from PostEntity p
        left join CommentEntity c on c.postId = p.id
        where p.status = com.blogly.blogly.domain.post.PostStatus.PUBLISHED
          and p.deletedAt is null
        group by p.id
        order by count(c) desc, p.id desc
        """,
        countQuery = """
        select count(p) from PostEntity p
        where p.status = com.blogly.blogly.domain.post.PostStatus.PUBLISHED
          and p.deletedAt is null
        """
    )
    fun findFeatured(pageable: Pageable): Page<PostEntity>
}
