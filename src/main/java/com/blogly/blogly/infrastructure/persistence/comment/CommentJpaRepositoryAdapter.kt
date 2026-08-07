package com.blogly.blogly.infrastructure.persistence.comment

import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CommentJpaRepositoryAdapter(
    private val repository: CommentJpaRepository,
    private val mapper: CommentDomainMapper
) : CommentRepository {

    override fun findById(id: CommentId): Comment? {
        return repository.findByIdOrNull(id.value)
            ?.let { mapper.toDomain(it) }
    }

    override fun findByPostId(postId: PostId): List<Comment> =
        repository.findByPostIdOrderByCreatedAtAsc(postId.value)
            .map(mapper::toDomain)

    override fun save(comment: Comment): CommentId {
        val entity = mapper.toEntity(comment)
        repository.save(entity)

        return CommentId(entity.id)
    }

    override fun deleteById(id: CommentId) {
        repository.deleteById(id.value)
    }
}
