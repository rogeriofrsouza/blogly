package com.blogly.blogly.application.comment

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.*
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotCommentableException
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentAccessGuard(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun resolveOwnedComment(postId: PostId, commentId: CommentId): Comment {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)
        if (!post.canBeCommentedOn()) {
            throw PostNotCommentableException(postId, post.status)
        }

        val comment = commentRepository.findByIdOrNull(commentId)
            ?.takeIf { it.belongsTo(postId) }
            ?: throw CommentNotFoundException(commentId)

        val user = userProvider.currentUser()
        if (!comment.isAuthoredBy(user.id)) {
            throw CommentNotOwnedException(commentId)
        }

        return comment
    }
}
