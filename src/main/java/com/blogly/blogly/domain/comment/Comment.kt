package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.shared.domainCheck
import com.blogly.blogly.domain.user.UserId
import kotlin.time.Clock
import kotlin.time.Instant

class Comment(
    val id: CommentId,
    var body: CommentBody,
    val postId: PostId,
    val userId: UserId,
    val parentId: CommentId? = null,
    val createdAt: Instant = Clock.System.now(),
    var updatedAt: Instant = createdAt,
    var deletedAt: Instant? = null
) {
    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun update(body: CommentBody) {
        this.body = body
        this.updatedAt = Clock.System.now()
    }

    fun reply(id: CommentId, body: CommentBody, userId: UserId): Comment =
        Comment(
            id = id,
            body = body,
            postId = this.postId,
            userId = userId,
            parentId = this.parentId ?: this.id
        )

    fun delete() {
        domainCheck(deletedAt == null) { CommentAlreadyDeletedException(id) }
        val now = Clock.System.now()
        this.deletedAt = now
        this.updatedAt = now
    }
}
