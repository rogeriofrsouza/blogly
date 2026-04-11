package com.blogly.blogly.domain.user;

import io.hypersistence.tsid.TSID;
import lombok.Getter;

import java.util.Objects;

@Getter
public class User {

    private final UserId id;
    private final Email email;
    private Password password;
    private Name name;

    public User(UserId id, Email email, Password password, Name name) {
        Objects.requireNonNull(id, "UserId cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(password, "Password cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User register(Email email, Password password, Name name) {
        return new User(
                new UserId(TSID.fast().toLong()),
                email,
                password,
                name
        );
    }
}
