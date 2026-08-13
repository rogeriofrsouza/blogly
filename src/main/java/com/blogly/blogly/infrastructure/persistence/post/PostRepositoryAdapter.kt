package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.*
import com.blogly.blogly.domain.user.UserId
import org.springframework.stereotype.Component

@Component
class PostRepositoryAdapter(
    private val repository: PostJdbcRepository
) : PostRepository {

    override fun findById(id: PostId): Post? =
        repository.findByIdAndStatusNot(id, PostStatus.DELETED)

    override fun findByUserId(userId: UserId): List<Post> =
        repository.findByUserIdAndStatusNot(userId, PostStatus.DELETED)

    override fun existsByTitle(title: Title): Boolean =
        repository.existsByTitleIgnoreCase(title)

    override fun save(post: Post): Post = repository.save(post)
}
