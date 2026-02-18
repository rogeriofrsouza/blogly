package com.blogly.blogly.blog.presentation;

import com.blogly.blogly.blog.application.usecase.CreateArticleUseCase;
import com.blogly.blogly.blog.domain.ArticleId;
import com.blogly.blogly.blog.presentation.dto.CreateArticleRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
class ArticleController {

    private final CreateArticleUseCase createUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateArticleRequestDto dto) {
        ArticleId id = createUseCase.execute(dto.toRequest());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.value())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
