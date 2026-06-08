package com.blogly.blogly.application.user;

import com.blogly.blogly.application.user.dto.UserDetailsResponse;
import com.blogly.blogly.domain.user.UserId;
import com.blogly.blogly.domain.user.UserNotFoundException;
import com.blogly.blogly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetUserByIdUseCase {

    private final UserRepository repository;

    public UserDetailsResponse execute(UserId id) {
        return Optional.ofNullable(repository.findById(id))
                .map(UserDetailsResponse::from)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
