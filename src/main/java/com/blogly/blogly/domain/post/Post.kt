package com.blogly.blogly.domain.post

import io.hypersistence.tsid.TSID

class Post(
    val id: PostId,
    val title: Title,
    val content: Content,
    val status: PostStatus
) {
    companion object {
        @JvmStatic
        fun create(title: Title, content: Content): Post {
            return Post(
                PostId(TSID.fast().toLong()),
                title,
                content,
                PostStatus.DRAFT
            )
        }
    }
}
