package com.example.blog.controller;

import com.example.blog.model.dto.ArticleResponseDto;
import com.example.blog.model.dto.CommentDto;
import com.example.blog.model.dto.CommentResponseDto;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.entity.Member;
import com.example.blog.model.mapper.MemberMapper;
import com.example.blog.repository.MemberRepository;
import com.example.blog.service.ArticleService;
import com.example.blog.service.CommentService;
import com.example.blog.service.MemberService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    ApiResponse<List<MemberDto>> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> dtos = MemberMapper.toDtos(members);
        return ApiResponse.createSuccessResponse(dtos);
    }

    @GetMapping("/{id}")
    ApiResponse<MemberDto> findMemberById(@PathVariable("id") Long id) {
        MemberDto dto = memberService.findById(id);
        return ApiResponse.createSuccessResponse(dto);
    }

    /*
        TODO: 수정 시, 특정 필드만 변경 가능하도록 수정
        - Ex. 비밀번호만 수정
        - Ex. 비밀번호를 제외한 필드를 수정
     */
    @PatchMapping("/{id}")
    ApiResponse<MemberDto> updateMemberInfo(
            @PathVariable("id") Long id, @Valid @RequestBody MemberDto memberDto
    ) {
        MemberDto responseDto = memberService.update(id, memberDto);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/{id}")
    ApiResponse<MemberDto> deleteMember(@PathVariable("id") Long id) {
        MemberDto dto = memberService.delete(id);
        return ApiResponse.createSuccessResponse(dto);
    }

    @GetMapping("/{id}/articles")
    public ApiResponse<List<ArticleResponseDto>> findArticlesOnMember(@PathVariable(name = "id") Long id) {
        List<ArticleResponseDto> result = articleService.findAll(id);
        return ApiResponse.createSuccessResponse(result);
    }

    @GetMapping("/{id}/comments")
    public ApiResponse<List<CommentResponseDto>> findCommentsOnMember(@PathVariable(name = "id") Long id) {
        List<CommentResponseDto> comments = commentService.findAllOnMember(id);
        return ApiResponse.createSuccessResponse(comments);
    }
}
