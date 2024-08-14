package com.example.blog.handler;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.model.dto.exception.ExceptionResponseDto;
import com.example.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleExceptionHandler {
    @ExceptionHandler
    public ApiResponse<ExceptionResponseDto> handleArticleNotFound(ArticleNotFoundException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.NOT_FOUND, e.getMessage());
        return ApiResponse.createFailureResponse(responseDto, HttpStatus.NOT_FOUND, e.getMessage());
    }
}
