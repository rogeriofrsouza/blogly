package com.blogly.blogly.presentation.auth;

import com.blogly.blogly.application.user.signup.SignUpResponse;
import com.blogly.blogly.application.user.signup.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
class AuthController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        SignUpResponse response = signUpUseCase.execute(dto.toRequest());

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
