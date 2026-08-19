package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.SliceResult
import org.springframework.stereotype.Component

@Component
class FindAllPostsUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    fun execute(query: PageQuery): SliceResult<PostDetailsResponse> {
        val user = userProvider.currentUser()

        return repository.findByUserId(user.id, query)
            .map(PostDetailsResponse::from)
    }
}
