package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId
import kotlin.time.Clock
import kotlin.time.Instant

class Post(
    val id: PostId,
    var title: Title,
    var content: Content,
    val userId: UserId,
    val status: PostStatus = PostStatus.PUBLISHED,
    val createdAt: Instant = Clock.System.now(),
    var updatedAt: Instant = createdAt,
    var deletedAt: Instant? = null
) {
    fun canBeCommentedOn(): Boolean = status == PostStatus.PUBLISHED

    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun update(title: Title, content: Content) {
        check(status != PostStatus.ARCHIVED) { "The post cannot be updated while it is archived" }
        this.title = title
        this.content = content
        this.updatedAt = Clock.System.now()
    }

    fun delete() {
        check(deletedAt == null) { "The post has already been deleted" }

        val now = Clock.System.now()
        this.deletedAt = now
        this.updatedAt = now
    }
}
