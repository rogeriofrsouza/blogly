package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.ArticleDto;
import com.blogly.blogly.domain.ArticleId;
import com.blogly.blogly.domain.ArticleRepository;
import com.blogly.blogly.domain.exception.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetArticleByIdUseCase {

    private final ArticleRepository repository;

    public ArticleDto execute(ArticleId id) {
        return repository.findById(id)
                .map(ArticleDto::from)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }
}
