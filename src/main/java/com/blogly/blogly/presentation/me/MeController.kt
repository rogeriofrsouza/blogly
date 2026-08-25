package com.blogly.blogly.presentation.me

import com.blogly.blogly.application.post.FindAuthoredPostsUseCase
import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.presentation.post.PostQueryParams
import com.blogly.blogly.presentation.shared.PageQueryParams
import com.blogly.blogly.presentation.shared.toPagedModel
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.web.PagedModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "me")
@RequestMapping("/api/me")
@RestController
class MeController(
    private val findAuthoredPostsUseCase: FindAuthoredPostsUseCase
) {
    @GetMapping("/posts")
    fun findPosts(
        @ParameterObject @Valid postParams: PostQueryParams,
        @ParameterObject @Valid pageParams: PageQueryParams
    ): PagedModel<PostDetailsResponse> =
        findAuthoredPostsUseCase.execute(postParams.toQuery(), pageParams.toQuery()).toPagedModel()
}
