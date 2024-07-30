package com.example.blog.controller;

import com.example.blog.model.Member;
import com.example.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    ResponseEntity<Object> findMemberById(@PathVariable("id") Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<Object> response = null;
        try {
            Member member = optionalMember.get();
            response = ResponseEntity.ok(member);
        } catch (NoSuchElementException exception) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @GetMapping("/members")
    ResponseEntity<List<Member>> findMembers() {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(members);
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
    ResponseEntity<Object> updateMemberInfo(@PathVariable("id") Long id, @RequestBody Member member) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<Object> response = null;

        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            findMember.setUsername(member.getUsername());
            findMember.setPassword(member.getPassword());
            findMember.setIntro(member.getIntro());

            memberRepository.save(findMember);
            response = ResponseEntity.ok(findMember);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /*
        TODO: 자격증명하여 인가된 사용자만 호출할 수 있도록 해야함
     */
    @DeleteMapping("/members/{id}")
    ResponseEntity<Object> deleteMember(@PathVariable("id") Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        ResponseEntity<Object> response = null;

        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            memberRepository.delete(findMember);
            response = ResponseEntity.ok(findMember);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;

    }
}
