package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CreateCommentRequest
import com.blogly.blogly.application.shared.IdGenerator
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotCommentableException
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import org.springframework.stereotype.Component

@Component
class CreateCommentUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider,
    private val idGenerator: IdGenerator
) {
    fun execute(postId: PostId, request: CreateCommentRequest): CommentId {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)

        if (!post.canBeCommentedOn()) {
            throw PostNotCommentableException(postId, post.status)
        }

        val userId = userProvider.currentUserId()

        val comment = Comment(
            id = CommentId(idGenerator.generate()),
            body = CommentBody(request.body),
            postId = postId,
            userId = userId
        )

        return repository.save(comment)
    }
}
