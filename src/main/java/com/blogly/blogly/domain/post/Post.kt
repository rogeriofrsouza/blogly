package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId

class Post(
    val id: PostId = PostId.generate(),
    val title: Title,
    val content: Content,
    val userId: UserId,
    val status: PostStatus = PostStatus.DRAFT
) {
    fun canBeCommentedOn(): Boolean = status == PostStatus.PUBLISHED
}
