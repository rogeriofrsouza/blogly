package com.blogly.blogly.infrastructure.bootstrap;

import com.blogly.blogly.application.user.InitializeAdminUseCase;
import com.blogly.blogly.application.user.UserAlreadyAdminException;
import com.blogly.blogly.application.user.dto.InitializeAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class AdminUserInitializer implements CommandLineRunner {

    private final InitializeAdminUseCase useCase;
    private final AdminProperties adminProperties;

    @Override
    public void run(String... args) {
        try {
            log.debug("Starting admin user initialization");

            var request = new InitializeAdminRequest(
                    adminProperties.email(),
                    adminProperties.password(),
                    adminProperties.name()
            );

            useCase.execute(request);
            log.info("Admin user initialization process finished");
        } catch (UserAlreadyAdminException _) {
            log.info("Admin user is already initialized, skipping initialization");
        } catch (Exception e) {
            log.error("Failed to initialize admin user", e);
        }
    }
}
