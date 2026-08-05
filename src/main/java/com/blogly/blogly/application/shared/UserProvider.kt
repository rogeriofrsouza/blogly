package com.blogly.blogly.application.shared

import com.blogly.blogly.domain.user.User

interface UserProvider {

    fun currentUser(): User
}
