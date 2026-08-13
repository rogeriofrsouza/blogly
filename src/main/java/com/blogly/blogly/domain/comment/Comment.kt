package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.user.UserId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import kotlin.time.Clock
import kotlin.time.Instant

@Table
data class Comment(
    @Id val id: CommentId,
    val body: CommentBody,
    val postId: PostId,
    val userId: UserId,
    @Version val version: Int? = null,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = createdAt
) {
    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun belongsTo(postId: PostId): Boolean = this.postId == postId

    fun update(body: CommentBody): Comment = copy(body = body, updatedAt = Clock.System.now())
}
