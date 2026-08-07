package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.UpdatePostRequest
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.*
import org.springframework.stereotype.Component

@Component
class UpdatePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId, request: UpdatePostRequest) {
        val post = repository.findById(postId) ?: throw PostNotFoundException(postId)
        val user = userProvider.currentUser()

        if (!post.isAuthoredBy(user.id)) {
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
