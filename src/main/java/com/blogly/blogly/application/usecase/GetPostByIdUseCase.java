package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.PostDto;
import com.blogly.blogly.domain.PostId;
import com.blogly.blogly.domain.PostRepository;
import com.blogly.blogly.domain.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetPostByIdUseCase {

    private final PostRepository repository;

    public PostDto execute(PostId id) {
        return repository.findById(id)
                .map(PostDto::from)
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}
