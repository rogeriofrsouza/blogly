package com.blogly.blogly.domain.exception

abstract class NotOwnedException(clazz: Class<*>, id: Long) :
    DomainException("${clazz.simpleName} $id does not belong to the current user")
