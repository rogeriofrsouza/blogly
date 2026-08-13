package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import kotlin.time.Clock
import kotlin.time.Instant

@Table
data class Post(
    @Id val id: PostId,
    val title: Title,
    val content: Content,
    val userId: UserId,
    val status: PostStatus = PostStatus.PUBLISHED,
    @Version val version: Int? = null,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = createdAt,
    val deletedAt: Instant? = null
) {
    fun canBeCommentedOn(): Boolean = status == PostStatus.PUBLISHED

    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun update(title: Title, content: Content): Post {
        check(status != PostStatus.ARCHIVED) { "The post cannot be updated while it is archived" }

        return copy(title = title, content = content, updatedAt = Clock.System.now())
    }

    fun archive(): Post {
        check(status != PostStatus.ARCHIVED) { "The post is already archived" }

        return copy(status = PostStatus.ARCHIVED, updatedAt = Clock.System.now())
    }

    fun delete(): Post {
        check(deletedAt == null) { "The post is already deleted" }

        val now = Clock.System.now()
        return copy(deletedAt = now, updatedAt = now)
    }
}
