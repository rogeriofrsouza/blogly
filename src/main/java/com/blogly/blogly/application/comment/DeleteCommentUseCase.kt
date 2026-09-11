package com.blogly.blogly.application.comment

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentNotFoundException
import com.blogly.blogly.domain.comment.CommentNotOwnedException
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.shared.domainCheck
import org.springframework.stereotype.Component

@Component
class DeleteCommentUseCase(
    private val repository: CommentRepository,
    private val userProvider: UserProvider
) {
    fun execute(commentId: CommentId) {
        val comment = repository.findById(commentId) ?: throw CommentNotFoundException(commentId)
        domainCheck(comment.isAuthoredBy(userProvider.currentUserId())) { CommentNotOwnedException(commentId) }

        if (repository.hasReplies(comment.id)) {
            comment.delete()
            repository.save(comment)
            return
        }

        repository.deleteById(comment.id)

        comment.parentId
            ?.let { repository.findDeletedById(it) }
            ?.takeUnless { repository.hasReplies(it.id) }
            ?.let { repository.deleteById(it.id) }
    }
}
