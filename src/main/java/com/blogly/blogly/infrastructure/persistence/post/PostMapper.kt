package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Content
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.Title
import org.springframework.stereotype.Component

@Component
class PostMapper {

    fun toEntity(post: Post): PostEntity {
        val entity = PostEntity()

        entity.id = post.id.value
        entity.title = post.title.value
        entity.content = post.content.value
        entity.status = post.status

        return entity
    }

    fun toDomain(entity: PostEntity) =
        Post(
            Title(entity.title),
            Content(entity.content),
            PostId(entity.id),
            entity.status
        )
}
