package com.example.blog.controller;

import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.Member;
import com.example.blog.repository.MemberRepository;
import com.example.blog.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    ResponseEntity<ApiResponse<Member>> findMemberById(@PathVariable("id") Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<ApiResponse<Member>> response = null;
        ApiResponse<Member> responseBody = null;

        try {
            Member member = optionalMember.get();
            responseBody = ApiResponse.createSuccessResponse(member);
            response = ResponseEntity.ok(responseBody);
        } catch (NoSuchElementException exception) {
            throw new MemberNotFoundException();
        }

        return response;
    }

    @GetMapping("/members")
    ResponseEntity<ApiResponse<List<Member>>> findMembers() {
        List<Member> members = memberRepository.findAll();
        ApiResponse<List<Member>> responseBody = ApiResponse.createSuccessResponse(members);
        return ResponseEntity.ok(responseBody);
    }

    /*
        TODO: 수정 시, 특정 필드만 변경 가능하도록 수정
        - Ex. 비밀번호만 수정
        - Ex. 비밀번호를 제외한 필드를 수정
     */
    /*
        ISSUE: DTO와 엔티티를 분리할 필요가 있어 보임.
        ISSUE: 수정 시 코드의 반복을 막기 위해 패턴 도입이 필요해보임.
     */
    @PatchMapping("/members/{id}")
    ResponseEntity<ApiResponse<Member>> updateMemberInfo(@PathVariable("id") Long id, @RequestBody Member member) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<ApiResponse<Member>> response = null;
        ApiResponse<Member> responseBody = null;

        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            findMember.setUsername(member.getUsername());
            findMember.setPassword(member.getPassword());
            findMember.setIntro(member.getIntro());

            memberRepository.save(findMember);
            responseBody = ApiResponse.createSuccessResponse(findMember);
            response = ResponseEntity.ok(ApiResponse.createSuccessResponse(findMember));
        } else {
            throw new MemberNotFoundException();
        }

        return response;
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/members/{id}")
    ResponseEntity<ApiResponse<Member>> deleteMember(@PathVariable("id") Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<ApiResponse<Member>> response = null;
        ApiResponse<Member> responseBody = null;

        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            memberRepository.delete(findMember);
            responseBody = ApiResponse.createSuccessResponse(findMember);
            response = ResponseEntity.ok(responseBody);
        } else {
            throw new MemberNotFoundException();
        }

        return response;

    }
}
