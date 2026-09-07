package com.blogly.blogly.application.comment

import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.application.comment.dto.toDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.exception.PostNotFoundException
import com.blogly.blogly.domain.shared.domainCheck
import org.springframework.stereotype.Component

@Component
class FindAllPostCommentsUseCase(
    private val repository: CommentRepository,
    private val postRepository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(postId: PostId): List<CommentDetailsResponse> {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException(postId)
        domainCheck(post.isVisibleTo(userProvider.currentUserIdOrNull())) { PostNotFoundException(postId) }

        return repository.findByPublishedPostId(postId)
            .map(Comment::toDetailsResponse)
    }
}
