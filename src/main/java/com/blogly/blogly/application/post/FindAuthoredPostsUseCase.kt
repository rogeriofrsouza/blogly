package com.blogly.blogly.application.post

import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.post.dto.toDetailsResponse
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostQuery
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindAuthoredPostsUseCase(
    private val repository: PostRepository,
    private val userProvider: UserProvider
) {
    @Transactional(readOnly = true)
    fun execute(postQuery: PostQuery, pageQuery: PageQuery): PageResult<PostDetailsResponse> {
        val user = userProvider.currentUser()

        return repository.findAllByAuthor(user.id, postQuery, pageQuery)
            .map(Post::toDetailsResponse)
    }
}
