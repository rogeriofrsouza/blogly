package com.blogly.blogly.application.comment

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.*
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotCommentableException
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import org.springframework.stereotype.Service

@Service
class CommentAccessGuard(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun resolveOwnedComment(commentId: CommentId): Comment {
        val comment = commentRepository.findById(commentId) ?: throw CommentNotFoundException(commentId)

        val post = postRepository.findById(comment.postId) ?: throw PostNotFoundException(comment.postId)
        if (!post.canBeCommentedOn()) {
            throw PostNotCommentableException(comment.postId, post.status)
        }

        val user = userProvider.currentUser()
        if (!comment.isAuthoredBy(user.id)) {
            throw CommentNotOwnedException(commentId)
        }

        return comment
    }
}
