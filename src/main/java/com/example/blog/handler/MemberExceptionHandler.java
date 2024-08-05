package com.example.blog.handler;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Member;
import com.example.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler
    public ApiResponse<MemberDto> handleDuplicatedMember(MemberDuplicatedException e) {
        return ApiResponse.createFailureResponse(null, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler
    public ApiResponse<MemberDto> handleMemberNotFound(MemberNotFoundException e) {
        return ApiResponse.createFailureResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    public ApiResponse<MemberDto> handleValidationViolation(MethodArgumentNotValidException e) {
        String simpleErrorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ApiResponse.createFailureResponse(null, HttpStatus.BAD_REQUEST, simpleErrorMessage);
    }

}
