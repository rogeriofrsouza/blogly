package com.blogly.blogly.application.post

import com.blogly.blogly.application.auth.UserProvider
import com.blogly.blogly.application.post.dto.CreatePostRequest
import com.blogly.blogly.domain.post.*
import org.springframework.stereotype.Component

@Component
class CreatePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(request: CreatePostRequest): PostId {
        val title = Title(request.title)

        if (repository.existsByTitle(title)) {
            throw TitleAlreadyExistsException(title)
        }

        val user = userProvider.currentUser()
        val post = Post(
            title = title,
            content = Content(request.content),
            userId = user.id
        )

        return repository.save(post)
    }
}
