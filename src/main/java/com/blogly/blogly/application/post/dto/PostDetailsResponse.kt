package com.blogly.blogly.application.post.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.post.Post

data class PostDetailsResponse(
    val id: String,
    val title: String,
    val content: String,
    val userId: String
)

fun Post.toDetailsResponse() =
    PostDetailsResponse(
        id = TsidCodec.encode(id.value),
        title = title.value,
        content = content.value,
        userId = TsidCodec.encode(userId.value)
    )
