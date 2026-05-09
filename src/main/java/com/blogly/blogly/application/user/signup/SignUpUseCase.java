package com.blogly.blogly.application.user.signup;

import com.blogly.blogly.application.dto.UserDto;
import com.blogly.blogly.domain.user.*;
import com.blogly.blogly.infrastructure.security.jwt.JwtService;
import com.blogly.blogly.infrastructure.security.userdetails.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;

    @Transactional
    public SignUpResponse execute(SignUpRequest request) {
        var email = new Email(request.email());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email.value());
        }

        var user = User.signUp(
                email,
                Password.create(request.password(), passwordHasher),
                new Name(request.name())
        );

        userRepository.save(user);

        var token = jwtService.generateToken(new SecurityUser(user));
        return new SignUpResponse(token, UserDto.from(user));
    }
}
