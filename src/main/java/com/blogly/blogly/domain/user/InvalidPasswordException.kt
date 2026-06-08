package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.exception.DomainException

class InvalidPasswordException(message: String) : DomainException(message)
