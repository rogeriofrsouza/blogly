package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(private val repository: PostRepository) {

    fun execute(id: PostId): PostDetailsResponse {
        return repository.findById(id)
            ?.let { PostDetailsResponse.from(it) }
            ?: throw PostNotFoundException(id)
    }
}
