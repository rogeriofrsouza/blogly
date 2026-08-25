package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.DomainException
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.domain.post.PostStatus

class PostNotEditableException(id: PostId, status: PostStatus) :
    DomainException("Post ${id.value} cannot be updated while $status")
