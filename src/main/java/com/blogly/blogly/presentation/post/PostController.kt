package com.blogly.blogly.presentation.post

import com.blogly.blogly.application.post.CreatePostUseCase
import com.blogly.blogly.application.post.FindAllPostsUseCase
import com.blogly.blogly.application.post.GetPostByIdUseCase
import com.blogly.blogly.application.post.UpdatePostUseCase
import com.blogly.blogly.application.post.dto.PostDetailsResponse
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.post.PostId
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RequestMapping("/api/posts")
@RestController
class PostController(
    private val createUseCase: CreatePostUseCase,
    private val getByIdUseCase: GetPostByIdUseCase,
    private val findAllPostsUseCase: FindAllPostsUseCase,
    private val updateUseCase: UpdatePostUseCase
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

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): PostDetailsResponse =
        getByIdUseCase.execute(PostId(TsidCodec.decode(id)))

    @GetMapping
    fun findAll(): List<PostDetailsResponse> = findAllPostsUseCase.execute()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @Valid @RequestBody dto: UpdatePostRequestDto
    ) = updateUseCase.execute(PostId(TsidCodec.decode(id)), dto.toRequest())
}
