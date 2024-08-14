package com.example.blog.handler;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.dto.exception.ExceptionResponseDto;
import com.example.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler
    public ApiResponse<ExceptionResponseDto> handleDuplicatedMember(MemberDuplicatedException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.CONFLICT, e.getMessage());
        return ApiResponse.createFailureResponse(responseDto, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler
    public ApiResponse<ExceptionResponseDto> handleMemberNotFound(MemberNotFoundException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.NOT_FOUND, e.getMessage());
        return ApiResponse.createFailureResponse(responseDto, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    public ApiResponse<ExceptionResponseDto> handleValidationViolation(MethodArgumentNotValidException e) {
        String simpleErrorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, simpleErrorMessage);
        return ApiResponse.createFailureResponse(responseDto, HttpStatus.BAD_REQUEST, simpleErrorMessage);
    }

}
