package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val repository: PostRepository,
    private val responseMapper: PostResponseMapper
) {
    fun execute(postId: PostId): PostDetailsResponse =
        repository.findById(postId)
            ?.let(responseMapper::toDetailsResponse)
            ?: throw PostNotFoundException(postId)
}
