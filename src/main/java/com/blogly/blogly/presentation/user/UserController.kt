package com.blogly.blogly.presentation.user

import com.blogly.blogly.application.user.GetUserByIdUseCase
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/users")
@RestController
class UserController(
    private val getByIdUseCase: GetUserByIdUseCase
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): UserDetailsResponse =
        getByIdUseCase.execute(id)
}
