package com.blogly.blogly.application.comment

import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import org.springframework.stereotype.Component

@Component
class DeleteCommentUseCase(
    private val repository: CommentRepository,
    private val accessGuard: CommentAccessGuard
) {
    fun execute(commentId: CommentId) {
        val comment = accessGuard.resolveOwnedComment(commentId)
        repository.deleteById(comment.id)
    }
}
