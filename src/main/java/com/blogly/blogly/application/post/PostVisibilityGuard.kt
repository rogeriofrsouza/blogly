package com.blogly.blogly.application.post

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import org.springframework.stereotype.Service

@Service
class PostVisibilityGuard(
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun resolveVisiblePost(postId: PostId): Post {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)

        if (!post.isVisibleTo(userProvider.currentUserOrNull()?.id)) {
            throw PostNotFoundException(postId)
        }

        return post
    }
}
