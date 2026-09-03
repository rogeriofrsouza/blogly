package com.blogly.blogly.application.user

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.user.Role
import org.springframework.stereotype.Service

@Service
class AdminAccessGuard(
    private val userProvider: UserProvider
) {
    fun requireAdmin() {
        if (userProvider.currentUserRole() != Role.ADMIN) {
            throw AdminPrivilegeRequiredException()
        }
    }
}
