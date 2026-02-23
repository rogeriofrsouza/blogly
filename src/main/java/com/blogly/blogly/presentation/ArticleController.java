package com.blogly.blogly.presentation;

import com.blogly.blogly.application.dto.ArticleDto;
import com.blogly.blogly.application.usecase.CreateArticleUseCase;
import com.blogly.blogly.application.usecase.GetArticleByIdUseCase;
import com.blogly.blogly.domain.ArticleId;
import com.blogly.blogly.presentation.dto.CreateArticleRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
class ArticleController {

    private final CreateArticleUseCase createUseCase;
    private final GetArticleByIdUseCase getByIdUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateArticleRequestDto dto) {
        ArticleId id = createUseCase.execute(dto.toRequest());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.value())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ArticleDto getById(@PathVariable Long id) {
        return getByIdUseCase.execute(new ArticleId(id));
    }
}
