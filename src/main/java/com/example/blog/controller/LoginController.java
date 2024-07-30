package com.example.blog.controller;

import com.example.blog.model.Member;
import com.example.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;

    @PostMapping("/register")
    ResponseEntity<Object> createMember(@RequestBody Member member) {
        Member findMember = memberRepository.findByUsername(member.getUsername());
        ResponseEntity<Object> response = null;
        if (findMember == null) {
            Member savedMember = memberRepository.save(member);
            String uriString = new StringBuilder().append("/members").append(savedMember.getId()).toString();
            URI location = URI.create(uriString);
            response = ResponseEntity.created(location).body(savedMember);
        } else {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }
}
