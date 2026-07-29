package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class PostNotCommentableException(id: PostId, status: PostStatus) :
    DomainException("Post ${id.value} does not accept comments while $status")
