package com.blogly.blogly.application.comment.dto

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.comment.Comment
import java.time.Instant
import kotlin.time.toJavaInstant

data class CommentDetailsResponse(
    val id: String,
    val body: String,
    val userId: String,
    val createdAt: Instant
) {
    companion object {
        fun from(comment: Comment) =
            CommentDetailsResponse(
                id = TsidCodec.encode(comment.id.value),
                body = comment.body.value,
                userId = TsidCodec.encode(comment.userId.value),
                createdAt = comment.createdAt.toJavaInstant()
            )
    }
}
