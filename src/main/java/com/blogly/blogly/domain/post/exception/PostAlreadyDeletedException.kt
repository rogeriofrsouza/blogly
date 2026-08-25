package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.DomainException
import com.blogly.blogly.domain.post.PostId

class PostAlreadyDeletedException(id: PostId) : DomainException("Post ${id.value} has already been deleted")
