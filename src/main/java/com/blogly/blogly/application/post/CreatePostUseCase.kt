package com.blogly.blogly.application.post

import com.blogly.blogly.application.auth.UserProvider
import com.blogly.blogly.application.post.dto.CreatePostRequest
import com.blogly.blogly.application.shared.IdProvider
import com.blogly.blogly.domain.post.*
import org.springframework.stereotype.Component

@Component
class CreatePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider,
    private val idProvider: IdProvider
) {
    fun execute(request: CreatePostRequest): String {
        val title = Title(request.title)

        if (repository.existsByTitle(title)) {
            throw TitleAlreadyExistsException(title)
        }

        val user = userProvider.currentUser()
        val post = Post(
            id = PostId(idProvider.generate()),
            title = title,
            content = Content(request.content),
            userId = user.id
        )

        return idProvider.encode(repository.save(post).value)
    }
}
