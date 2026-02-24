package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.CreatePostRequest;
import com.blogly.blogly.domain.Post;
import com.blogly.blogly.domain.PostId;
import com.blogly.blogly.domain.PostRepository;
import com.blogly.blogly.domain.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreatePostUseCase {

    private final PostRepository repository;

    public PostId execute(CreatePostRequest request) {
        var post = new Post(new Title(request.title()), request.body(), request.summary());

        return repository.save(post);
    }
}
