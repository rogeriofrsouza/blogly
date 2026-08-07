package com.blogly.blogly.application.comment

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentNotFoundException
import com.blogly.blogly.domain.comment.CommentNotOwnedException
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotCommentableException
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class DeleteCommentUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId, commentId: CommentId) {
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

        repository.deleteById(commentId)
    }
}
