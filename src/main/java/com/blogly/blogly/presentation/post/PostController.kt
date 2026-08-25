package com.blogly.blogly.presentation.post

import com.blogly.blogly.application.post.*
import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.post.PostId
import com.blogly.blogly.presentation.shared.PageQueryParams
import com.blogly.blogly.presentation.shared.toPagedModel
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Tag(name = "posts")
@RequestMapping("/api/posts")
@RestController
class PostController(
    private val createUseCase: CreatePostUseCase,
    private val getByIdUseCase: GetPostByIdUseCase,
    private val findFeaturedPostsUseCase: FindFeaturedPostsUseCase,
    private val updateUseCase: UpdatePostUseCase,
    private val deleteUseCase: DeletePostUseCase,
    private val archiveUseCase: ArchivePostUseCase
) {
    @PostMapping
    fun create(@Valid @RequestBody dto: CreatePostRequestDto): ResponseEntity<Void> {
        val id = createUseCase.execute(dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(TsidCodec.encode(id.value))
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/featured")
    fun findFeatured(@ParameterObject @Valid pageParams: PageQueryParams): PagedModel<PostDetailsResponse> =
        findFeaturedPostsUseCase.execute(pageParams.toQuery()).toPagedModel()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): PostDetailsResponse =
        getByIdUseCase.execute(PostId(TsidCodec.decode(id)))

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @Valid @RequestBody dto: UpdatePostRequestDto
    ) = updateUseCase.execute(PostId(TsidCodec.decode(id)), dto.toRequest())

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = deleteUseCase.execute(PostId(TsidCodec.decode(id)))

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/archive")
    fun archive(@PathVariable id: String) = archiveUseCase.execute(PostId(TsidCodec.decode(id)))
}
