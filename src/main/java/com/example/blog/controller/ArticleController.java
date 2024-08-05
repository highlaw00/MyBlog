package com.example.blog.controller;

import com.example.blog.model.dto.ArticleDto;
import com.example.blog.service.ArticleService;
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

    @GetMapping
    public ApiResponse<List<ArticleDto>> findAll() {
        List<ArticleDto> result = articleService.findAll();
        return ApiResponse.createSuccessResponse(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<ArticleDto> findOne(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ArticleDto> remove(@PathVariable(name = "id") Long id) {
        return ApiResponse.createSuccessResponse(articleService.delete(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<ArticleDto> updateOne(@PathVariable(name = "id") Long id, @Valid @RequestBody ArticleDto dto) {
        dto.setId(id);
        return ApiResponse.createSuccessResponse(articleService.update(dto));
    }

    @PostMapping()
    public ApiResponse<ArticleDto> write(@RequestBody ArticleDto dto) {
        return ApiResponse.createSuccessResponse(articleService.post(dto), HttpStatus.CREATED);
    }
}
