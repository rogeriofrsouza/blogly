package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.*
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.infrastructure.persistence.post.PostSpecifications.authoredBy
import com.blogly.blogly.infrastructure.persistence.post.PostSpecifications.contentContains
import com.blogly.blogly.infrastructure.persistence.post.PostSpecifications.hasStatus
import com.blogly.blogly.infrastructure.persistence.post.PostSpecifications.notDeleted
import com.blogly.blogly.infrastructure.persistence.post.PostSpecifications.titleContains
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository

@Repository
class PostJpaRepositoryAdapter(
    private val repository: PostJpaRepository
) : PostRepository {

    override fun findById(id: PostId): Post? {
        return repository.findByIdAndDeletedAtIsNull(id.value)
            ?.let { PostDomainMapper.toDomain(it) }
    }

    override fun findAllByAuthor(userId: UserId, postQuery: PostQuery, pageQuery: PageQuery): PageResult<Post> {
        val specifications = buildList {
            add(notDeleted())
            add(authoredBy(userId.value))
            postQuery.status?.let { add(hasStatus(it)) }
            postQuery.title?.let { add(titleContains(it)) }
            postQuery.content?.let { add(contentContains(it)) }
        }
        val pageable = PageRequest.of(pageQuery.page, pageQuery.size, Sort.by(Sort.Direction.DESC, "id"))
        val posts = repository.findAll(Specification.allOf(specifications), pageable)

        return PageResult(
            posts.content.map(PostDomainMapper::toDomain),
            pageQuery.page,
            pageQuery.size,
            posts.totalElements
        )
    }

    override fun save(post: Post): PostId {
        val entity = PostDomainMapper.toEntity(post)
        repository.save(entity)

        return PostId(entity.id)
    }

    override fun existsByTitle(title: Title): Boolean {
        return repository.existsByTitleIgnoreCase(title.value)
    }
}
