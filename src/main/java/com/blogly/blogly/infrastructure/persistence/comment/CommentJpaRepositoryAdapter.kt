package com.blogly.blogly.infrastructure.persistence.comment

import com.blogly.blogly.domain.comment.Comment
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.comment.CommentRepository
import com.blogly.blogly.domain.post.PostId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CommentJpaRepositoryAdapter(
    private val repository: CommentJpaRepository
) : CommentRepository {

    override fun findById(id: CommentId): Comment? {
        return repository.findByIdOrNull(id.value)
            ?.let { CommentDomainMapper.toDomain(it) }
    }

    override fun findByPublishedPostId(postId: PostId): List<Comment> =
        repository.findByPublishedPostId(postId.value)
            .map(CommentDomainMapper::toDomain)

    override fun save(comment: Comment): CommentId {
        val entity = CommentDomainMapper.toEntity(comment)
        repository.save(entity)

        return CommentId(entity.id)
    }

    override fun deleteById(id: CommentId) {
        repository.deleteById(id.value)
    }
}
