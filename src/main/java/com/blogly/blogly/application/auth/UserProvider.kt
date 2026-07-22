package com.blogly.blogly.application.auth

import com.blogly.blogly.domain.user.User

interface UserProvider {

    fun currentUser(): User
}
