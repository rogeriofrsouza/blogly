package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.NotFoundException
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId

class PostNotFoundException(id: PostId) : NotFoundException(Post::class.java, id.value)
