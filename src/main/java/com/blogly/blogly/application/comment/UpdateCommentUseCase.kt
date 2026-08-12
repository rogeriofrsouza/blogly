package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import org.springframework.stereotype.Component

@Component
class UpdateCommentUseCase(
    private val repository: CommentRepository,
    private val accessGuard: CommentAccessGuard
) {
    fun execute(postId: PostId, commentId: CommentId, request: UpdateCommentRequest) {
        val comment = accessGuard.resolveOwnedComment(postId, commentId)

        repository.save(
            comment.update(CommentBody(request.body))
        )
    }
}
