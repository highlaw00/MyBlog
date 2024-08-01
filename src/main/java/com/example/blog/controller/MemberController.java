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

    @GetMapping("/{id}")
    ApiResponse<MemberDto> findMemberById(@PathVariable("id") Long id) {
        Member member = memberService.find(id);
        MemberDto dto = MemberMapper.toDto(member);
        return ApiResponse.createSuccessResponse(dto);
    }

    @GetMapping
    ApiResponse<List<MemberDto>> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> dtos = MemberMapper.toDtos(members);
        return ApiResponse.createSuccessResponse(dtos);
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
        Member updatedMember = memberService.update(id, memberDto);
        MemberDto responseDto = MemberMapper.toDto(updatedMember);
        return ApiResponse.createSuccessResponse(responseDto);
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/{id}")
    ApiResponse<MemberDto> deleteMember(@PathVariable("id") Long id) {
        Member deletedMember = memberService.delete(id);
        MemberDto dto = MemberMapper.toDto(deletedMember);
        return ApiResponse.createSuccessResponse(dto);
    }
}
