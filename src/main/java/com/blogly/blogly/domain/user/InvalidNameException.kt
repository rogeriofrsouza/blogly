package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.exception.DomainException

class InvalidNameException(message: String) : DomainException(message)
