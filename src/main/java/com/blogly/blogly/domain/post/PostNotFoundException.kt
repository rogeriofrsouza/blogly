package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.NotFoundException

class PostNotFoundException(id: PostId) : NotFoundException(Post::class.java, id.value)
