package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId

interface CommentRepository {

    fun findById(id: CommentId): Comment?

    fun findDeletedById(id: CommentId): Comment?

    fun findByPublishedPostId(postId: PostId): List<Comment>

    fun hasReplies(id: CommentId): Boolean

    fun save(comment: Comment): CommentId

    fun deleteById(id: CommentId)
}
