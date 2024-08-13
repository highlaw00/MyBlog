package com.example.blog.service;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.dto.RegisterDto;
import com.example.blog.model.entity.Member;
import com.example.blog.model.enums.MemberRole;
import com.example.blog.model.mapper.MemberMapper;
import com.example.blog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto delete(Long id) {
        Member member = this.find(id);
        memberRepository.deleteById(member.getId());
        return MemberMapper.toDto(member);
    }

    public Member findById(Long id) {
        return this.find(id);
    }

    public MemberDto findByIdAsDto(Long id) {
        Member findMember = this.find(id);
        return MemberMapper.toDto(findMember);
    }

    public MemberDto findByName(String username) {
        return MemberMapper.toDto(memberRepository.findByUsername(username));
    }

    public MemberDto post(RegisterDto dto) {
        String username = dto.getUsername();
        String rawPassword = dto.getPassword();

        Boolean isExist = memberRepository.existsByUsername(username);

        if (isExist) {
            throw new MemberDuplicatedException();
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);
        dto.setPassword(hashedPassword);

        Member member = MemberMapper.toEntity(dto);
        member.setRole(MemberRole.ROLE_USER);
        member = memberRepository.save(member);

        return MemberMapper.toDto(member);
    }

    public MemberDto update(Long id, MemberDto memberDto) {
        Member member = this.find(id);

        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setIntro(memberDto.getIntro());

        return MemberMapper.toDto(memberRepository.save(member));
    }

    private Member find(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }
}
