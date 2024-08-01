package com.example.blog.handler;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.entity.Member;
import com.example.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<Member>> handleDuplicatedMember(MemberDuplicatedException e) {
        ApiResponse<Member> responseBody = ApiResponse.createFailureResponse(null, HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Member>> handleMemberNotFound(MemberNotFoundException e) {
        ApiResponse<Member> responseBody = ApiResponse.createFailureResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
