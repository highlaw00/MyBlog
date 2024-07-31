package com.example.blog.controller;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.model.Member;
import com.example.blog.repository.MemberRepository;
import com.example.blog.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<Member>> createMember(@RequestBody Member member) {
        Member findMember = memberRepository.findByUsername(member.getUsername());
        ResponseEntity<ApiResponse<Member>> response = null;
        ApiResponse<Member> responseBody = null;

        if (findMember == null) {
            Member savedMember = memberRepository.save(member);
            String uriString = new StringBuffer().append("/members").append(savedMember.getId()).toString();
            URI location = URI.create(uriString);
            responseBody = ApiResponse.createSuccessResponse(savedMember, HttpStatus.CREATED);
            response = ResponseEntity.created(location).body(responseBody);
        } else {
            throw new MemberDuplicatedException();
        }

        return response;
    }
}
