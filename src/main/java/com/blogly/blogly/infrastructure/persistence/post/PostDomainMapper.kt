package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Content
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.user.UserId
import org.springframework.stereotype.Component

@Component
class PostDomainMapper {

    fun toEntity(post: Post) =
        PostEntity().apply {
            id = post.id.value
            title = post.title.value
            content = post.content.value
            status = post.status
            userId = post.userId.value
        }

    fun toDomain(entity: PostEntity) =
        Post(
            PostId(entity.id),
            Title(entity.title),
            Content(entity.content),
            UserId(entity.userId),
            entity.status
        )
}
