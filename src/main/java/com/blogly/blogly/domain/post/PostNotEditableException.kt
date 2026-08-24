package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class PostNotEditableException(id: PostId, status: PostStatus) :
    DomainException("Post ${id.value} cannot be updated while $status")
