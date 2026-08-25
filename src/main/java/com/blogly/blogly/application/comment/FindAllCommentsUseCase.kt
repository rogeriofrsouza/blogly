package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.application.comment.dto.toDetailsResponse
import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import org.springframework.stereotype.Component

@Component
class FindAllCommentsUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository
) {
    fun execute(postId: PostId): List<CommentDetailsResponse> {
        postRepository.findById(postId) ?: throw PostNotFoundException(postId)

        return repository.findByPublishedPostId(postId)
            .map(Comment::toDetailsResponse)
    }
}
