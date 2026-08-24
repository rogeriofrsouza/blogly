package com.blogly.blogly.domain.post

data class PostQuery(
    val title: String? = null,
    val content: String? = null,
    val status: PostStatus = PostStatus.PUBLISHED
)
