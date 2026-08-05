package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class FindAllPostsUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider,
    private val responseMapper: PostResponseMapper
) {
    fun execute(): List<PostDetailsResponse> {
        val user = userProvider.currentUser()

        return repository.findByUserId(user.id)
            .map(responseMapper::toDetailsResponse)
    }
}
