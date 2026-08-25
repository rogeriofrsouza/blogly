package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.application.comment.dto.toDetailsResponse
import com.blogly.blogly.application.post.PostVisibilityGuard
import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import org.springframework.stereotype.Component

@Component
class FindAllCommentsUseCase(
    private val repository: CommentRepository,
    private val visibilityGuard: PostVisibilityGuard
) {
    fun execute(postId: PostId): List<CommentDetailsResponse> {
        visibilityGuard.resolveVisiblePost(postId)

        return repository.findByPublishedPostId(postId)
            .map(Comment::toDetailsResponse)
    }
}
