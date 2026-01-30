package com.blogly.blogly.blog.infrastructure.persistence;

import com.blogly.blogly.blog.domain.Article;
import com.blogly.blogly.blog.domain.ArticleId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {

    @Mapping(target = "id", source = "id.value")
    ArticleEntity toEntity(Article article);

    @Mapping(target = "id", source = "id")
    Article toDomain(ArticleEntity entity);

    default ArticleId mapId(Long id) {
        return id != null ? ArticleId.from(id) : null;
    }
}
