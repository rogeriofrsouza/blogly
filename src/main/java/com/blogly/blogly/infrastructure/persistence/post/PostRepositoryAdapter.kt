package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.user.UserId
import org.springframework.stereotype.Component

@Component
class PostRepositoryAdapter(
    private val repository: PostJdbcRepository
) : PostRepository {

    override fun findById(id: PostId): Post? =
        repository.findByIdAndDeletedAtIsNull(id)

    override fun findByUserId(userId: UserId): List<Post> =
        repository.findByUserIdAndDeletedAtIsNull(userId)

    override fun existsByTitle(title: Title): Boolean =
        repository.existsByTitleIgnoreCase(title)

    override fun save(post: Post): Post = repository.save(post)
}
