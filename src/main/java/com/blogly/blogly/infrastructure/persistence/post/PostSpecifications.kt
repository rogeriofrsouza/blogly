package com.blogly.blogly.infrastructure.persistence.post

import com.blogly.blogly.domain.post.PostStatus
import com.blogly.blogly.infrastructure.persistence.shared.TextSpecifications.containsIgnoreCase
import org.springframework.data.jpa.domain.Specification
import java.time.Instant

object PostSpecifications {

    fun notDeleted() = Specification<PostEntity> { root, _, builder ->
        builder.isNull(root.get<Instant>("deletedAt"))
    }

    fun authoredBy(userId: Long) = Specification<PostEntity> { root, _, builder ->
        builder.equal(root.get<Long>("userId"), userId)
    }

    fun hasStatus(status: PostStatus) = Specification<PostEntity> { root, _, builder ->
        builder.equal(root.get<PostStatus>("status"), status)
    }

    fun titleContains(title: String) = containsIgnoreCase<PostEntity>("title", title)

    fun contentContains(content: String) = containsIgnoreCase<PostEntity>("content", content)
}
