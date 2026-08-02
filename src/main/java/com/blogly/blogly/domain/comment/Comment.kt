package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.user.UserId
import kotlin.time.Clock
import kotlin.time.Instant

class Comment(
    val id: CommentId = CommentId.generate(),
    val body: CommentBody,
    val postId: PostId,
    val userId: UserId,
    val createdAt: Instant = Clock.System.now()
)
