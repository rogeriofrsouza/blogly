package com.blogly.blogly.infrastructure.persistence.user;

import com.blogly.blogly.domain.user.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId().value());
        entity.setEmail(user.getEmail().value());
        entity.setPassword(user.getPassword().hashedValue());
        entity.setName(user.getName().value());

        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                new Name(entity.getName())
        );
    }
}
