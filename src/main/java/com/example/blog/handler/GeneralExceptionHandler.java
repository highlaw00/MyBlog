package com.example.blog.handler;

import com.example.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler
    public ApiResponse<?> handleGeneralException(Exception ex) {
        return ApiResponse.createFailureResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
