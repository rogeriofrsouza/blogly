package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.CreatePostRequest
import com.blogly.blogly.domain.post.*
import com.blogly.blogly.domain.post.Post.Companion.create
import org.springframework.stereotype.Component

@Component
class CreatePostUseCase(private val repository: PostRepository) {

    fun execute(request: CreatePostRequest): PostId {
        val title = Title(request.title)

        if (repository.existsByTitle(title)) {
            throw TitleAlreadyExistsException(title)
        }

        val post = create(title, Content(request.content))

        return repository.save(post)
    }
}
