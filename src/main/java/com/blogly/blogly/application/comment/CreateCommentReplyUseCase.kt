package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CreateCommentRequest
import com.blogly.blogly.application.shared.IdGenerator
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentNotFoundException
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotCommentableException
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import com.blogly.blogly.domain.shared.domainCheck
import org.springframework.stereotype.Component

@Component
class CreateCommentReplyUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val idGenerator: IdGenerator,
    private val userProvider: UserProvider,
) {
    fun execute(commentId: CommentId, request: CreateCommentRequest): CommentId {
        val parent = repository.findById(commentId) ?: throw CommentNotFoundException(commentId)
        val post = postRepository.findById(parent.postId) ?: throw PostNotFoundException(parent.postId)

        domainCheck(post.canBeCommentedOn()) { PostNotCommentableException(post.id, post.status) }

        val reply = parent.reply(
            id = CommentId(idGenerator.generate()),
            body = CommentBody(request.body),
            userId = userProvider.currentUserId()
        )

        return repository.save(reply)
    }
}
