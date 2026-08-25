package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val repository: PostRepository
) {
    fun execute(postId: PostId): PostDetailsResponse =
        repository.findById(postId)
            ?.let(Post::toDetailsResponse)
            ?: throw PostNotFoundException(postId)
}
