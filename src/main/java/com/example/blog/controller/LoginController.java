package com.example.blog.controller;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Member;
import com.example.blog.repository.MemberRepository;
import com.example.blog.service.MemberService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<Member>> createMember(@Valid @RequestBody MemberDto dto) {
        memberService.checkDuplication(dto);
        ResponseEntity<ApiResponse<Member>> response = null;
        ApiResponse<Member> responseBody = null;

        Member savedMember = memberService.postMember(dto);
        URI location = URI.create("/members" + savedMember.getId());
        responseBody = ApiResponse.createSuccessResponse(savedMember, HttpStatus.CREATED);
        response = ResponseEntity.created(location).body(responseBody);

        return response;
    }
}
