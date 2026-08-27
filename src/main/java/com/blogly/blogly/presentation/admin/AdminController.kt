package com.blogly.blogly.presentation.admin

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.application.user.CreateUserUseCase
import com.blogly.blogly.application.user.GetUserDetailsUseCase
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.UserId
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Tag(name = "admin")
@RequestMapping("/api/admin")
@RestController
class AdminController(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) {
    @PostMapping("/users")
    fun createUser(@Valid @RequestBody dto: CreateUserDto): ResponseEntity<Void> {
        val id = createUserUseCase.execute(dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(TsidCodec.encode(id.value))
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/users/{id}")
    fun getUserDetails(@PathVariable id: String): UserDetailsResponse =
        getUserDetailsUseCase.execute(UserId(TsidCodec.decode(id)))
}
