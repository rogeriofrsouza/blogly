package com.blogly.blogly.presentation.user

import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.application.user.CreateUserUseCase
import com.blogly.blogly.application.user.GetUserByIdUseCase
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.domain.user.UserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RequestMapping("/api/users")
@RestController
class UserController(
    private val createUseCase: CreateUserUseCase,
    private val getByIdUseCase: GetUserByIdUseCase
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
    fun getById(@PathVariable id: String): UserDetailsResponse =
        getByIdUseCase.execute(UserId(TsidCodec.decode(id)))
}
