package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class FindAllCommentsUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository
) {
    fun execute(postId: PostId): List<CommentDetailsResponse> {
        postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException(postId)

        return repository.findByPostIdOrderByCreatedAtAsc(postId)
            .map(CommentDetailsResponse::from)
    }
}
