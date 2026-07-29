package com.blogly.blogly.application.post.dto

import com.blogly.blogly.domain.post.Post

data class PostDetailsResponse(
    val id: String,
    val title: String,
    val content: String
) {
    companion object {
        fun from(post: Post): PostDetailsResponse {
            return PostDetailsResponse(
                post.id.value.toString(),
                post.title.value,
                post.content.value
            )
        }
    }
}
