package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.*
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotCommentableException
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class UpdateCommentUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId, commentId: CommentId, request: UpdateCommentRequest) {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)
        if (!post.canBeCommentedOn()) {
            throw PostNotCommentableException(postId, post.status)
        }

        val comment = repository.findById(commentId)
            ?.takeIf { it.belongsTo(postId) }
            ?: throw CommentNotFoundException(commentId)

        val user = userProvider.currentUser()
        if (!comment.isAuthoredBy(user.id)) {
            throw CommentNotOwnedException(commentId)
        }

        comment.update(CommentBody(request.body))
        repository.save(comment)
    }
}
