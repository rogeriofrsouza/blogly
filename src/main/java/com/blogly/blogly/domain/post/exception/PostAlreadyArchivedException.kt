package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.DomainException
import com.blogly.blogly.domain.post.PostId

class PostAlreadyArchivedException(id: PostId) : DomainException("Post ${id.value} has already been archived")
