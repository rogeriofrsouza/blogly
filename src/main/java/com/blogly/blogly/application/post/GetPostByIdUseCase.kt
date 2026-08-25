package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.domain.post.PostId
import org.springframework.stereotype.Component

@Component
class GetPostByIdUseCase(
    private val visibilityGuard: PostVisibilityGuard
) {
    fun execute(postId: PostId): PostDetailsResponse =
        visibilityGuard.resolveVisiblePost(postId)
            .toDetailsResponse()
}
