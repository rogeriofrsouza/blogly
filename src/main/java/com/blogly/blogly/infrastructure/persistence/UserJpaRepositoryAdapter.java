package com.blogly.blogly.infrastructure.persistence;

import com.blogly.blogly.domain.user.Email;
import com.blogly.blogly.domain.user.User;
import com.blogly.blogly.domain.user.UserId;
import com.blogly.blogly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findById(UserId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email.value())
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(email.value());
    }

    @Override
    public UserId save(User user) {
        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);
        return new UserId(entity.getId());
    }
}
