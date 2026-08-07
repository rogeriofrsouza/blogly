package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.user.UserId
import kotlin.time.Clock
import kotlin.time.Instant

class Comment(
    val id: CommentId,
    var body: CommentBody,
    val postId: PostId,
    val userId: UserId,
    val createdAt: Instant = Clock.System.now(),
    var updatedAt: Instant = createdAt
) {
    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun belongsTo(postId: PostId): Boolean = this.postId == postId

    fun update(body: CommentBody) {
        this.body = body
        this.updatedAt = Clock.System.now()
    }
}
