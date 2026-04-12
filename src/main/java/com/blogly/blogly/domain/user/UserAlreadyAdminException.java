package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.exception.DomainException;

public class UserAlreadyAdminException extends DomainException {

    public UserAlreadyAdminException() {
        super("User is already an admin");
    }
}
