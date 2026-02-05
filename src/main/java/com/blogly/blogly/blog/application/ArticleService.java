package com.blogly.blogly.blog.application;

import com.blogly.blogly.blog.application.dto.CreateArticleDto;
import com.blogly.blogly.blog.domain.Article;
import com.blogly.blogly.blog.domain.ArticleId;
import com.blogly.blogly.blog.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleId createArticle(CreateArticleDto dto) {
        var article = new Article(null, dto.title(), dto.body(), dto.summary());
        return repository.save(article);
    }
}
