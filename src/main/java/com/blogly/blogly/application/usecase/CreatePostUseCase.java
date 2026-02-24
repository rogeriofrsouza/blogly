package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.CreatePostRequest;
import com.blogly.blogly.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreatePostUseCase {

    private final PostRepository repository;

    public PostId execute(CreatePostRequest request) {
        var post = new Post(
                new Title(request.title()),
                new Content(request.content())
        );

        return repository.save(post);
    }
}
