package com.blogly.blogly.application.comment.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.comment.Comment
import java.time.Instant
import kotlin.time.toJavaInstant

data class CommentDetailsResponse(
    val id: String,
    val body: String,
    val userId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)

fun Comment.toDetailsResponse() =
    CommentDetailsResponse(
        id = TsidCodec.encode(id.value),
        body = body.value,
        userId = TsidCodec.encode(userId.value),
        createdAt = createdAt.toJavaInstant(),
        updatedAt = updatedAt.toJavaInstant(),
    )
