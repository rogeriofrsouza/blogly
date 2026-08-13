package com.blogly.blogly.application.post.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.post.Post
import java.time.Instant
import kotlin.time.toJavaInstant

data class PostDetailsResponse(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
) {
    companion object {
        fun from(post: Post) =
            PostDetailsResponse(
                id = TsidCodec.encode(post.id.value),
                title = post.title.value,
                content = post.content.value,
                createdAt = post.createdAt.toJavaInstant(),
            )
    }
}
