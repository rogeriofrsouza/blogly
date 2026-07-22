package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.user.UserId
import io.hypersistence.tsid.TSID

class Post(
    val title: Title,
    val content: Content,
    val userId: UserId,
    val id: PostId = PostId(TSID.fast().toLong()),
    val status: PostStatus = PostStatus.DRAFT
)
