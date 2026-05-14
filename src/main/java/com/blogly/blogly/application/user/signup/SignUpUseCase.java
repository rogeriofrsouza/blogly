package com.blogly.blogly.application.user.signup;

import com.blogly.blogly.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

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

        return new SignUpResponse(user.getId().value(), user.getEmail().value(), user.getRole());
    }
}
