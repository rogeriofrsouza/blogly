package com.blogly.blogly.blog.application.usecase;

import com.blogly.blogly.blog.application.dto.ArticleDto;
import com.blogly.blogly.blog.domain.ArticleId;
import com.blogly.blogly.blog.domain.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetArticleByIdUseCase {

    private final ArticleRepository repository;

    public ArticleDto execute(ArticleId id) {
        return repository.findById(id)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + id.value()));
    }
}
