package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.CreatePostRequest
import com.blogly.blogly.application.shared.IdGenerator
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.*
import com.blogly.blogly.domain.post.exception.TitleAlreadyExistsException
import org.springframework.stereotype.Component

@Component
class CreatePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider,
    private val idGenerator: IdGenerator
) {
    fun execute(request: CreatePostRequest): PostId {
        val title = Title(request.title)

        if (repository.existsByTitle(title)) {
            throw TitleAlreadyExistsException(title)
        }

        val userId = userProvider.currentUserId()
        val post = Post(
            id = PostId(idGenerator.generate()),
            title = title,
            content = Content(request.content),
            userId = userId
        )

        return repository.save(post)
    }
}
