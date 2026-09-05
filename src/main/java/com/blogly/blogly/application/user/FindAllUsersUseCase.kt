package com.blogly.blogly.application.user

import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.application.user.dto.toDetailsResponse
import com.blogly.blogly.domain.shared.PageQuery
import com.blogly.blogly.domain.shared.PageResult
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class FindAllUsersUseCase(
    private val repository: UserRepository,
    private val adminAccessGuard: AdminAccessGuard
) {
    fun execute(pageQuery: PageQuery): PageResult<UserDetailsResponse> {
        adminAccessGuard.requireAdmin()

        return repository.findAll(pageQuery)
            .map(User::toDetailsResponse)
    }
}
