package com.blogly.blogly.presentation.auth

import com.blogly.blogly.application.auth.SignInUseCase
import com.blogly.blogly.application.auth.SignUpUseCase
import com.blogly.blogly.application.auth.dto.SignInResponse
import com.blogly.blogly.application.auth.dto.SignUpResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Tag(name = "auth")
@RequestMapping("/auth")
@RestController
class AuthController(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody dto: SignUpRequestDto): ResponseEntity<SignUpResponse> {
        val response = signUpUseCase.execute(dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/users/{id}")
            .buildAndExpand(response.id)
            .toUri()

        return ResponseEntity.created(location).body(response)
    }

    @PostMapping("/signin")
    fun signIn(@Valid @RequestBody dto: SignInRequestDto): ResponseEntity<SignInResponse> {
        val response = signInUseCase.execute(dto.toRequest())
        return ResponseEntity.ok(response)
    }
}
