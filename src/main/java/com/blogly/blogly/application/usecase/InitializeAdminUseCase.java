package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.InitializeAdminRequest;
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
                .orElse(User.register(
                        email,
                        Password.create(request.password(), passwordHasher),
                        new Name(request.name())
                ));

        if (!user.isAdmin()) {
            user.promoteToAdmin();
            userRepository.save(user);
        }
    }
}
