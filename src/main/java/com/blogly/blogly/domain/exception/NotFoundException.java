package com.blogly.blogly.domain.exception;

public abstract class NotFoundException extends DomainException {

    protected NotFoundException(Class<?> clazz, Long id) {
        super("%s not found with id: %d".formatted(clazz.getSimpleName(), id));
    }
}
