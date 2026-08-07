package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.NotOwnedException

class PostNotOwnedException(id: PostId) : NotOwnedException(Post::class.java, id.value)
