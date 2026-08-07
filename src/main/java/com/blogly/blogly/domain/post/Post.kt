package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId

class Post(
    val id: PostId,
    var title: Title,
    var content: Content,
    val userId: UserId,
    val status: PostStatus = PostStatus.PUBLISHED
) {
    fun canBeCommentedOn(): Boolean = status == PostStatus.PUBLISHED

    fun isAuthoredBy(userId: UserId): Boolean = this.userId == userId

    fun update(title: Title, content: Content) {
        check(status != PostStatus.ARCHIVED) { "The post cannot be updated while it is archived" }
        this.title = title
        this.content = content
    }
}
