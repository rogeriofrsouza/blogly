package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.user.User
import org.springframework.stereotype.Service

@Service
class AdminAccessGuard(
    private val userProvider: UserProvider
) {
    fun requireAdmin(): User {
        val user = userProvider.currentUser()

        if (!user.isAdmin) {
            throw AdminPrivilegeRequiredException()
        }

        return user
    }
}
