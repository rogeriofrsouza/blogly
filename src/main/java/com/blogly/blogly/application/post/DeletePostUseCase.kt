package com.blogly.blogly.application.post

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostNotFoundException
import com.blogly.blogly.domain.post.PostNotOwnedException
import com.blogly.blogly.domain.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class DeletePostUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId) {
        val post = repository.findByIdOrNull(postId) ?: throw PostNotFoundException(postId)
        val user = userProvider.currentUser()

        if (!post.isAuthoredBy(user.id)) {
            throw PostNotOwnedException(postId)
        }

        repository.save(post.delete())
    }
}
