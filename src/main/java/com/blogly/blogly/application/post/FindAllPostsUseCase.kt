package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.stereotype.Component

@Component
class FindAllPostsUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(): List<PostDetailsResponse> {
        val user = userProvider.currentUser()

        return repository.findByUserId(user.id)
            .map(Post::toDetailsResponse)
    }
}
