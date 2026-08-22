package com.blogly.blogly.infrastructure.persistence.comment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommentJpaRepository : JpaRepository<CommentEntity, Long> {

    @Query(
        """
        select c from CommentEntity c
        join PostEntity p on p.id = c.postId
        where c.postId = :postId
          and p.status = com.blogly.blogly.domain.post.PostStatus.PUBLISHED
          and p.deletedAt is null
        order by c.createdAt asc
        """
    )
    fun findByPublishedPostId(postId: Long): List<CommentEntity>
}
