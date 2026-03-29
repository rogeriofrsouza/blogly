package com.blogly.blogly.domain.exception;

public class TitleAlreadyExistsException extends DomainException {

    public TitleAlreadyExistsException(String title) {
        super("There is already a post with title: " + title);
    }
}
