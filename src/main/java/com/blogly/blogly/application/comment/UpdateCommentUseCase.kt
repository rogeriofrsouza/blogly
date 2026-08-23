package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import org.springframework.stereotype.Component

@Component
class UpdateCommentUseCase(
    private val repository: CommentRepository,
    private val accessGuard: CommentAccessGuard
) {
    fun execute(commentId: CommentId, request: UpdateCommentRequest) {
        val comment = accessGuard.resolveOwnedComment(commentId)
        comment.update(CommentBody(request.body))

        repository.save(comment)
    }
}
