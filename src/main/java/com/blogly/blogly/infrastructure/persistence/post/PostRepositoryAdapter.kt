package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostRepository
import com.blogly.blogly.domain.post.Title
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import com.blogly.blogly.domain.user.UserId
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PostRepositoryAdapter(
    private val repository: PostJdbcRepository
) : PostRepository {

    override fun findById(id: PostId): Post? =
        repository.findByIdAndDeletedAtIsNull(id)

    override fun findByUserId(userId: UserId, query: PageQuery): PageResult<Post> =
        repository.findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(
            userId,
            PageRequest.of(query.page, query.size)
        ).let { PageResult(it.content, it.number, it.size, it.totalElements) }

    override fun existsByTitle(title: Title): Boolean =
        repository.existsByTitleIgnoreCase(title)

    override fun save(post: Post): Post = repository.save(post)
}
