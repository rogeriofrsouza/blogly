package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.user.UserId
import org.springframework.stereotype.Repository

@Repository
class PostJpaRepositoryAdapter(
    private val repository: PostJpaRepository
) : PostRepository {

    override fun findById(id: PostId): Post? {
        return repository.findByIdAndDeletedAtIsNull(id.value)
            ?.let { PostDomainMapper.toDomain(it) }
    }

    override fun findByUserId(userId: UserId): List<Post> =
        repository.findByUserIdAndDeletedAtIsNull(userId.value)
            .map(PostDomainMapper::toDomain)

    override fun save(post: Post): PostId {
        val entity = PostDomainMapper.toEntity(post)
        repository.save(entity)

        return PostId(entity.id)
    }

    override fun existsByTitle(title: Title): Boolean {
        return repository.existsByTitleIgnoreCase(title.value)
    }
}
