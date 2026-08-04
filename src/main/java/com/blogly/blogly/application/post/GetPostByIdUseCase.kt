package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.IdProvider
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val repository: PostRepository,
    private val idProvider: IdProvider,
    private val responseMapper: PostResponseMapper
) {
    fun execute(id: String): PostDetailsResponse {
        val postId = PostId(idProvider.decode(id))

        return repository.findById(postId)
            ?.let(responseMapper::toDetailsResponse)
            ?: throw PostNotFoundException(postId)
    }
}
