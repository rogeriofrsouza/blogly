package com.blogly.blogly.application.shared

import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.Role
import com.blogly.blogly.domain.user.UserId

interface UserProvider {

    fun currentUserId(): UserId

    fun currentUserIdOrNull(): UserId?

    fun currentUserEmail(): Email

    fun currentUserRole(): Role
}
