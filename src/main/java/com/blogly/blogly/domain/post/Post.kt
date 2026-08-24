package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.shared.domainCheck
import com.blogly.blogly.domain.user.UserId
import kotlin.time.Clock
import kotlin.time.Instant

class Post(
    val id: PostId,
    var title: Title,
    var content: Content,
    val userId: UserId,
    var status: PostStatus = PostStatus.PUBLISHED,
    val createdAt: Instant = Clock.System.now(),
    var updatedAt: Instant = createdAt,
    var deletedAt: Instant? = null
) {
    fun canBeCommentedOn(): Boolean = status == PostStatus.PUBLISHED

    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun update(title: Title, content: Content) {
        domainCheck(status != PostStatus.ARCHIVED) { PostNotEditableException(id, status) }
        this.title = title
        this.content = content
        this.updatedAt = Clock.System.now()
    }

    fun archive() {
        domainCheck(status != PostStatus.ARCHIVED) { PostAlreadyArchivedException(id) }
        this.status = PostStatus.ARCHIVED
        this.updatedAt = Clock.System.now()
    }

    fun delete() {
        domainCheck(deletedAt == null) { PostAlreadyDeletedException(id) }
        val now = Clock.System.now()
        this.deletedAt = now
        this.updatedAt = now
    }
}
