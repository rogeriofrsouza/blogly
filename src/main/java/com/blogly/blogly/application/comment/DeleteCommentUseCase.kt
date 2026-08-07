package com.blogly.blogly.application.comment

import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import org.springframework.stereotype.Component

@Component
class DeleteCommentUseCase(
    private val repository: CommentRepository,
    private val accessGuard: CommentAccessGuard
) {
    fun execute(postId: PostId, commentId: CommentId) {
        val comment = accessGuard.resolveOwnedComment(postId, commentId)
        repository.deleteById(comment.id)
    }
}
