package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import com.blogly.blogly.domain.user.UserId

interface PostRepository {

    fun findById(id: PostId): Post?

    fun findAllByAuthor(userId: UserId, postQuery: PostQuery, pageQuery: PageQuery): PageResult<Post>

    fun save(post: Post): PostId

    fun existsByTitle(title: Title): Boolean
}
