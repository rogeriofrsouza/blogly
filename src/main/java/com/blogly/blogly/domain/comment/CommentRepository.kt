package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, CommentId> {

    fun findByPostIdOrderByCreatedAtAsc(postId: PostId): List<Comment>
}
