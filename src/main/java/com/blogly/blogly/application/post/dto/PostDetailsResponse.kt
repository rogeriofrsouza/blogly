package com.blogly.blogly.application.post.dto

import com.blogly.blogly.domain.post.Post

data class PostDetailsResponse(
    val id: Long,
    val title: String,
    val content: String
) {
    companion object {
        fun from(post: Post): PostDetailsResponse {
            return PostDetailsResponse(
                post.id.value,
                post.title.value,
                post.content.value
            )
        }
    }
}
