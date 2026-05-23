package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UserId id) {
        super(User.class, id.value());
    }
}
