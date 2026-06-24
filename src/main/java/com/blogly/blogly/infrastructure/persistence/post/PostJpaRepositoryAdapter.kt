package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.Title
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PostJpaRepositoryAdapter(
    private val repository: PostJpaRepository,
    private val mapper: PostDomainMapper
) : PostRepository {

    override fun findById(id: PostId): Post? {
        return repository.findByIdOrNull(id.value)
            ?.let { mapper.toDomain(it) }
    }

    override fun save(post: Post): PostId {
        val entity = mapper.toEntity(post)
        repository.save(entity)

        return PostId(entity.id)
    }

    override fun existsByTitle(title: Title): Boolean {
        return repository.existsByTitleIgnoreCase(title.value)
    }
}
