package com.blogly.blogly.presentation.user;

import com.blogly.blogly.application.user.signup.SignUpResponse;
import com.blogly.blogly.application.user.signup.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
class AuthController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/signup")
    public SignUpResponse signUp(@Valid @RequestBody SignUpRequestDto dto) {
        return signUpUseCase.execute(dto.toRequest());
    }
}
