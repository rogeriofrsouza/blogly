package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.post.Post
import org.springframework.stereotype.Component

@Component
class PostResponseMapper {

    fun toDetailsResponse(post: Post) =
        PostDetailsResponse(
            id = TsidCodec.encode(post.id.value),
            title = post.title.value,
            content = post.content.value
        )
}
