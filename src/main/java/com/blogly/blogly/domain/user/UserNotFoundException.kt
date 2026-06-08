package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.exception.NotFoundException

class UserNotFoundException(id: UserId) : NotFoundException(User::class.java, id.value)
