package com.blogly.blogly.domain.post

import io.hypersistence.tsid.TSID

class Post(
    val title: Title,
    val content: Content,
    val id: PostId = PostId(TSID.fast().toLong()),
    val status: PostStatus = PostStatus.DRAFT
)
