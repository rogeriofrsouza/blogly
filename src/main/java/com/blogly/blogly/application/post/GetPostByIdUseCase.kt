package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val repository: PostRepository
) {
    fun execute(postId: PostId): PostDetailsResponse =
        repository.findByIdOrNull(postId)
            ?.let(PostDetailsResponse::from)
            ?: throw PostNotFoundException(postId)
}
