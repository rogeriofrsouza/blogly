package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.SliceResult
import com.blogly.blogly.domain.user.UserId

interface PostRepository {

    fun findById(id: PostId): Post?

    fun findByUserId(userId: UserId, query: PageQuery): SliceResult<Post>

    fun existsByTitle(title: Title): Boolean

    fun save(post: Post): Post
}
