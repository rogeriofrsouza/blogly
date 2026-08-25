package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import org.springframework.stereotype.Component

@Component
class FindFeaturedPostsUseCase(
    private val repository: PostRepository
) {
    fun execute(pageQuery: PageQuery): PageResult<PostDetailsResponse> =
        repository.findFeatured(pageQuery)
            .map(Post::toDetailsResponse)
}
