package com.blogly.blogly.domain.user.exception

import com.blogly.blogly.domain.exception.NotFoundException
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId

class UserNotFoundException(id: UserId) : NotFoundException(User::class.java, id.value)
