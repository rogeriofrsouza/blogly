package com.blogly.blogly.application.post.dto

import com.blogly.blogly.domain.post.Post
import io.hypersistence.tsid.TSID

data class PostDetailsResponse(
    val id: String,
    val title: String,
    val content: String
) {
    companion object {
        fun from(post: Post): PostDetailsResponse {
            return PostDetailsResponse(
                TSID(post.id.value).toLowerCase(),
                post.title.value,
                post.content.value
            )
        }
    }
}
