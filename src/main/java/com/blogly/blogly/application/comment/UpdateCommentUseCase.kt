package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.UpdateCommentRequest
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.*
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotCommentableException
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import com.blogly.blogly.domain.shared.domainCheck
import org.springframework.stereotype.Component

@Component
class UpdateCommentUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(commentId: CommentId, request: UpdateCommentRequest) {
        val comment = repository.findById(commentId) ?: throw CommentNotFoundException(commentId)
        val post = postRepository.findById(comment.postId) ?: throw PostNotFoundException(comment.postId)

        domainCheck(post.canBeCommentedOn()) { PostNotCommentableException(post.id, post.status) }
        domainCheck(comment.isAuthoredBy(userProvider.currentUserId())) { CommentNotOwnedException(commentId) }

        comment.update(CommentBody(request.body))
        repository.save(comment)
    }
}
