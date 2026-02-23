package com.blogly.blogly.presentation;

import com.blogly.blogly.application.dto.PostDto;
import com.blogly.blogly.application.usecase.CreatePostUseCase;
import com.blogly.blogly.application.usecase.GetPostByIdUseCase;
import com.blogly.blogly.domain.PostId;
import com.blogly.blogly.presentation.dto.CreatePostRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@RestController
class PostController {

    private final CreatePostUseCase createUseCase;
    private final GetPostByIdUseCase getByIdUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreatePostRequestDto dto) {
        PostId id = createUseCase.execute(dto.toRequest());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.value())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        return getByIdUseCase.execute(new PostId(id));
    }
}
