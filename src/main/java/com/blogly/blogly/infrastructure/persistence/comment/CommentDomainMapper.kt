package com.blogly.blogly.infrastructure.persistence.comment

import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentBody
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.user.UserId
import org.springframework.stereotype.Component
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant

@Component
class CommentDomainMapper {

    fun toEntity(comment: Comment) =
        CommentEntity().apply {
            id = comment.id.value
            body = comment.body.value
            postId = comment.postId.value
            userId = comment.userId.value
            createdAt = comment.createdAt.toJavaInstant()
        }

    fun toDomain(entity: CommentEntity) =
        Comment(
            CommentId(entity.id),
            CommentBody(entity.body),
            PostId(entity.postId),
            UserId(entity.userId),
            entity.createdAt.toKotlinInstant()
        )
}
