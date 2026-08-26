package com.blogly.blogly.presentation.user

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.application.user.CreateUserUseCase
import com.blogly.blogly.application.user.GetUserProfileUseCase
import com.blogly.blogly.application.user.dto.UserProfileResponse
import com.blogly.blogly.domain.user.UserId
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Tag(name = "users")
@RequestMapping("/api/users")
@RestController
class UserController(
    private val createUseCase: CreateUserUseCase,
    private val getProfileUseCase: GetUserProfileUseCase
) {
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateUserDto): ResponseEntity<Void> {
        val id = createUseCase.execute(dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(TsidCodec.encode(id.value))
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{id}")
    fun getProfile(@PathVariable id: String): UserProfileResponse =
        getProfileUseCase.execute(UserId(TsidCodec.decode(id)))
}
