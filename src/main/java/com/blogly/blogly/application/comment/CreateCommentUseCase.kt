package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CreateCommentRequest
import com.blogly.blogly.application.shared.IdProvider
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotCommentableException
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class CreateCommentUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider,
    private val idProvider: IdProvider
) {
    fun execute(postIdRaw: String, request: CreateCommentRequest): String {
        val postId = PostId(idProvider.decode(postIdRaw))
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)

        if (!post.canBeCommentedOn()) {
            throw PostNotCommentableException(postId, post.status)
        }

        val user = userProvider.currentUser()

        val comment = Comment(
            id = CommentId(idProvider.generate()),
            body = CommentBody(request.body),
            postId = postId,
            userId = user.id
        )

        return idProvider.encode(repository.save(comment).value)
    }
}
