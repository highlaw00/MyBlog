package com.example.blog.controller;

import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.dto.member.MemberPostRequestDto;
import com.example.blog.service.MemberService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/register")
    ApiResponse<MemberResponseDto> createMember(@Valid @RequestBody MemberPostRequestDto dto) {
        MemberResponseDto savedMemberDto = memberService.post(dto);
        return ApiResponse.createSuccessResponse(savedMemberDto, HttpStatus.CREATED);
    }
}
