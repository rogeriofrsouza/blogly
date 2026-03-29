package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.CreatePostRequest;
import com.blogly.blogly.domain.exception.TitleAlreadyExistsException;
import com.blogly.blogly.domain.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreatePostUseCase {

    private final PostRepository repository;

    public PostId execute(CreatePostRequest request) {
        var title = new Title(request.title());

        if (repository.existsByTitle(title)) {
            throw new TitleAlreadyExistsException(title.value());
        }

        var post = Post.create(
                title,
                new Content(request.content()));

        return repository.save(post);
    }
}
