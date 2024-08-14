package com.example.blog.controller;

import com.example.blog.model.dto.comment.CommentPatchRequestDto;
import com.example.blog.model.dto.comment.CommentPostRequestDto;
import com.example.blog.model.dto.comment.CommentResponseDto;
import com.example.blog.service.CommentService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ApiResponse<CommentResponseDto> postComment(@Valid @RequestBody CommentPostRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.post(requestDto);
        return ApiResponse.createSuccessResponse(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/comments/{id}")
    public ApiResponse<CommentResponseDto> findOne(@PathVariable(name = "id") Long id) {
        CommentResponseDto responseDto = commentService.findById(id);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    @PatchMapping("/comments/{id}")
    public ApiResponse<CommentResponseDto> updateComment(@PathVariable(name = "id") Long id, @Valid @RequestBody CommentPatchRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.update(id, requestDto);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    @DeleteMapping("/comments/{id}")
    public ApiResponse<Integer> deleteComment(@PathVariable(name = "id") Long id) {
        int deleted;
        ApiResponse<Integer> response;
        try {
            deleted = commentService.delete(id);
            response = ApiResponse.createSuccessResponse(deleted);
        } catch (Exception e) {
            deleted = 0;
            response = ApiResponse.createSuccessResponse(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
