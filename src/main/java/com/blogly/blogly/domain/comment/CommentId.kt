package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.shared.EntityId

@JvmInline
value class CommentId(override val value: Long) : EntityId {
    init {
        require(value > 0) { "CommentId value must be positive" }
    }
}
