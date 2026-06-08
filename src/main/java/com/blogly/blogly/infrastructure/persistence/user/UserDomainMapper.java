package com.blogly.blogly.infrastructure.persistence.user;

import com.blogly.blogly.domain.user.*;
import org.springframework.stereotype.Component;

@Component
public class UserDomainMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId().getValue());
        entity.setEmail(user.getEmail().getValue());
        entity.setPassword(user.getPassword().getHashedValue());
        entity.setName(user.getName().getValue());
        entity.setRole(user.getRole());

        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                new Name(entity.getName()),
                entity.getRole()
        );
    }
}
