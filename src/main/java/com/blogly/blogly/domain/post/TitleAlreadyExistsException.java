package com.blogly.blogly.domain.post;

import com.blogly.blogly.domain.shared.DomainException;

public class TitleAlreadyExistsException extends DomainException {

    public TitleAlreadyExistsException(String title) {
        super("There is already a post with title: " + title);
    }
}
