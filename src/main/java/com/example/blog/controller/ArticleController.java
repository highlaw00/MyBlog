package com.example.blog.controller;

import com.example.blog.model.dto.article.ArticlePostRequestDto;
import com.example.blog.model.dto.article.ArticleResponseDto;
import com.example.blog.model.dto.article.ArticleUpdateRequestDto;
import com.example.blog.model.dto.comment.CommentResponseDto;
import com.example.blog.service.ArticleService;
import com.example.blog.service.CommentService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    public ApiResponse<List<ArticleResponseDto>> findAll() {
        return ApiResponse.createSuccessResponse(articleService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ArticleResponseDto> readArticle(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.findByIdAsResponseDto(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ArticleResponseDto> remove(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.delete(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<ArticleResponseDto> updateOne(@PathVariable(name = "id") Long id, @Valid @RequestBody ArticleUpdateRequestDto requestDto) {
        return ApiResponse.createSuccessResponse(articleService.update(id, requestDto));
    }

    @PostMapping()
    public ApiResponse<ArticleResponseDto> write(@RequestBody ArticlePostRequestDto requestDto) {
        return ApiResponse.createSuccessResponse(articleService.post(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ApiResponse<List<CommentResponseDto>> findCommentsOnArticle(@PathVariable(name = "id") Long id) {
        List<CommentResponseDto> dtos = commentService.findAllOnArticle(id);
        return ApiResponse.createSuccessResponse(dtos);
    }
}
