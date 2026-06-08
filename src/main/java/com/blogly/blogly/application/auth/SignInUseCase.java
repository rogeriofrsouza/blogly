package com.blogly.blogly.application.auth;

import com.blogly.blogly.application.auth.dto.SignInRequest;
import com.blogly.blogly.application.auth.dto.SignInResponse;
import com.blogly.blogly.domain.user.Email;
import com.blogly.blogly.domain.user.PasswordHasher;
import com.blogly.blogly.domain.user.User;
import com.blogly.blogly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SignInUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public SignInResponse execute(SignInRequest request) {
        var email = new Email(request.email());

        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(InvalidCredentialsException::new);

        if (!user.verifyPassword(request.password(), passwordHasher)) {
            throw new InvalidCredentialsException();
        }

        String token = tokenProvider.generateToken(user);

        return new SignInResponse(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getRole(),
                token);
    }
}
