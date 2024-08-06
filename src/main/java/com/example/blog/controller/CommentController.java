package com.example.blog.controller;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.CommentDto;
import com.example.blog.model.dto.CommentResponseDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.mapper.ArticleMapper;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.CommentRepository;
import com.example.blog.service.ArticleService;
import com.example.blog.service.CommentService;
import com.example.blog.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ArticleRepository articleRepository;

    @GetMapping("/comments/{id}")
    public ApiResponse<CommentResponseDto> findOne(@PathVariable(name = ("id")) Long id) {
        CommentResponseDto dto = commentService.findById(id);
        return ApiResponse.createSuccessResponse(dto);
    }

}
