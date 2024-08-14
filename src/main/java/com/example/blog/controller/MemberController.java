package com.example.blog.controller;

import com.example.blog.model.dto.article.ArticleResponseDto;
import com.example.blog.model.dto.comment.CommentResponseDto;
import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.dto.member.MemberUpdateRequestDto;
import com.example.blog.service.ArticleService;
import com.example.blog.service.CommentService;
import com.example.blog.service.MemberService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    ApiResponse<List<MemberResponseDto>> findMembers() {
        List<MemberResponseDto> responseDtos = memberService.findAll();
        return ApiResponse.createSuccessResponse(responseDtos);
    }

    @GetMapping("/{id}")
    ApiResponse<MemberResponseDto> findMemberById(@PathVariable(name = "id") Long id) {
        MemberResponseDto responseDto = memberService.findByIdAsDto(id);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    /*
        TODO: 수정 시, 특정 필드만 변경 가능하도록 수정
        - Ex. 비밀번호만 수정
        - Ex. 비밀번호를 제외한 필드를 수정
     */
    @PatchMapping("/{id}")
    ApiResponse<MemberResponseDto> updateMemberInfo(@PathVariable(name = "id") Long id, @Valid @RequestBody MemberUpdateRequestDto memberDto) {
        MemberResponseDto responseDto = memberService.update(id, memberDto);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/{id}")
    ApiResponse<MemberResponseDto> deleteMember(@PathVariable(name = "id") Long id) {
        MemberResponseDto responseDto = memberService.delete(id);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    @GetMapping("/{id}/articles")
    public ApiResponse<List<ArticleResponseDto>> findArticlesOnMember(@PathVariable(name = "id") Long id) {
        List<ArticleResponseDto> responseDtos = articleService.findAll(id);
        return ApiResponse.createSuccessResponse(responseDtos);
    }

    @GetMapping("/{id}/comments")
    public ApiResponse<List<CommentResponseDto>> findCommentsOnMember(@PathVariable(name = "id") Long id) {
        List<CommentResponseDto> responseDtos = commentService.findAllOnMember(id);
        return ApiResponse.createSuccessResponse(responseDtos);
    }
}
