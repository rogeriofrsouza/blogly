package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.UpdatePostRequest
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.Content
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import com.blogly.blogly.domain.post.exception.PostNotOwnedException
import com.blogly.blogly.domain.post.exception.TitleAlreadyExistsException
import org.springframework.stereotype.Component

@Component
class UpdatePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId, request: UpdatePostRequest) {
        val post = repository.findById(postId) ?: throw PostNotFoundException(postId)
        val userId = userProvider.currentUserId()

        if (!post.isAuthoredBy(userId)) {
            throw PostNotOwnedException(postId)
        }

        val title = Title(request.title)

        if (title != post.title && repository.existsByTitle(title)) {
            throw TitleAlreadyExistsException(title)
        }

        post.update(title, Content(request.content))
        repository.save(post)
    }
}
