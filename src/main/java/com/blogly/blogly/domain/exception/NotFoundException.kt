package com.blogly.blogly.domain.exception

abstract class NotFoundException(clazz: Class<*>, id: Long) :
    DomainException("${clazz.simpleName} not found with id: $id")
