package com.blogly.blogly.infrastructure.persistence.user;

import com.blogly.blogly.domain.user.Email;
import com.blogly.blogly.domain.user.User;
import com.blogly.blogly.domain.user.UserId;
import com.blogly.blogly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserDomainMapper mapper;

    @Override
    public User findById(UserId id) {
        return repository.findById(id.getValue())
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public User findByEmail(Email email) {
        return repository.findByEmail(email.getValue())
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(email.getValue());
    }

    @Override
    public UserId save(User user) {
        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);
        return user.getId();
    }
}
