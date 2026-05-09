package com.blogly.blogly.application.user.initialize;

import com.blogly.blogly.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class InitializeAdminUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Transactional
    public void execute(InitializeAdminRequest request) {
        var email = new Email(request.email());

        User user = userRepository.findByEmail(email)
                .orElse(User.signUp(
                        email,
                        Password.create(request.password(), passwordHasher),
                        new Name(request.name())
                ));

        if (user.isAdmin()) {
            throw new UserAlreadyAdminException();
        }

        user.promoteToAdmin();
        userRepository.save(user);
    }
}
