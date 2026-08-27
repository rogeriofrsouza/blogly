package com.blogly.blogly.presentation.user

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.application.user.GetUserProfileUseCase
import com.blogly.blogly.application.user.dto.UserProfileResponse
import com.blogly.blogly.domain.user.UserId
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users")
@RequestMapping("/api/users")
@RestController
class UserController(
    private val getProfileUseCase: GetUserProfileUseCase
) {
    @GetMapping("/{id}")
    fun getProfile(@PathVariable id: String): UserProfileResponse =
        getProfileUseCase.execute(UserId(TsidCodec.decode(id)))
}
