package com.blogly.blogly.presentation.me

import com.blogly.blogly.application.post.FindAuthoredPostsUseCase
import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.user.GetCurrentUserUseCase
import com.blogly.blogly.application.user.UpdateProfileUseCase
import com.blogly.blogly.application.user.dto.UserDetailsResponse
import com.blogly.blogly.presentation.post.PostQueryParams
import com.blogly.blogly.presentation.shared.PageQueryParams
import com.blogly.blogly.presentation.shared.toPagedModel
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "me")
@RequestMapping("/api/me")
@RestController
class MeController(
    private val findAuthoredPostsUseCase: FindAuthoredPostsUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
) {
    @GetMapping
    fun getUser(): UserDetailsResponse = getCurrentUserUseCase.execute()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/profile")
    fun updateProfile(@Valid @RequestBody dto: UpdateProfileDto) =
        updateProfileUseCase.execute(dto.toRequest())

    @GetMapping("/posts")
    fun findPosts(
        @ParameterObject @Valid postParams: PostQueryParams,
        @ParameterObject @Valid pageParams: PageQueryParams
    ): PagedModel<PostDetailsResponse> =
        findAuthoredPostsUseCase.execute(postParams.toQuery(), pageParams.toQuery()).toPagedModel()
}
