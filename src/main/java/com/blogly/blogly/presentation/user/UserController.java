package com.blogly.blogly.presentation.user;

import com.blogly.blogly.application.user.GetUserByIdUseCase;
import com.blogly.blogly.application.user.dto.UserDetailsResponse;
import com.blogly.blogly.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
class UserController {

    private final GetUserByIdUseCase getByIdUseCase;

    @GetMapping("/{id}")
    UserDetailsResponse getById(@PathVariable Long id) {
        return getByIdUseCase.execute(new UserId(id));
    }
}
