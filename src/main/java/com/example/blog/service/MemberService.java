package com.example.blog.service;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.entity.Member;
import com.example.blog.model.mapper.MemberMapper;
import com.example.blog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void checkDuplication(MemberDto dto) {
        Member member = memberRepository.findByUsername(dto.getUsername());
        if (member != null) {
            throw new MemberDuplicatedException();
        }
    }

    @Transactional
    public Member updateMember(Long id, MemberDto memberDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setIntro(memberDto.getIntro());

        return member;
    }

    public Member postMember(MemberDto dto) {
        Member member = MemberMapper.toEntity(dto);
        member = memberRepository.save(member);
        return member;
    }
}
