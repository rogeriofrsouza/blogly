package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId

interface CommentRepository {

    fun findById(id: CommentId): Comment?

    fun findByPublishedPostId(postId: PostId): List<Comment>

    fun save(comment: Comment): CommentId

    fun deleteById(id: CommentId)
}
