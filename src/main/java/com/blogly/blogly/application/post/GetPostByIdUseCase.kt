package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import com.blogly.blogly.domain.shared.domainCheck
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId): PostDetailsResponse {
        val post = repository.findById(postId) ?: throw PostNotFoundException(postId)
        domainCheck(post.isVisibleTo(userProvider.currentUserIdOrNull())) { PostNotFoundException(postId) }

        return post.toDetailsResponse()
    }
}
