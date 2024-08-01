package com.example.blog.controller;

import com.example.blog.model.dto.MemberDto;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.entity.Member;
import com.example.blog.model.mapper.MemberMapper;
import com.example.blog.repository.MemberRepository;
import com.example.blog.service.MemberService;
import com.example.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/{id}")
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

    @GetMapping
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
    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<MemberDto>> updateMemberInfo(
            @PathVariable("id") Long id, @Valid @RequestBody MemberDto memberDto
    ) {
        Member updatedMember = memberService.updateMember(id, memberDto);
        MemberDto responseDto = MemberMapper.toDto(updatedMember);
        return ResponseEntity.ok(ApiResponse.createSuccessResponse(responseDto));
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/{id}")
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
