package com.example.blog.controller;

import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.ArticleResponseDto;
import com.example.blog.model.dto.CommentResponseDto;
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

    @GetMapping("/{id}")
    public ApiResponse<ArticleResponseDto> findOne(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ArticleResponseDto> remove(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.delete(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<ArticleResponseDto> updateOne(@PathVariable(name = "id") Long id, @Valid @RequestBody ArticleDto dto) {
        dto.setId(id);
        return ApiResponse.createSuccessResponse(articleService.update(dto));
    }

    @PostMapping()
    public ApiResponse<ArticleResponseDto> write(@RequestBody ArticleDto dto) {
        return ApiResponse.createSuccessResponse(articleService.post(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ApiResponse<List<CommentResponseDto>> findCommentsOnArticle(@PathVariable(name = "id") Long id) {
        List<CommentResponseDto> dtos = commentService.findAllOnArticle(id);
        return ApiResponse.createSuccessResponse(dtos);
    }
}
