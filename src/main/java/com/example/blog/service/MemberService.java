package com.example.blog.service;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.dto.member.MemberUpdateRequestDto;
import com.example.blog.model.dto.member.MemberPostRequestDto;
import com.example.blog.model.entity.Member;
import com.example.blog.model.enums.MemberRole;
import com.example.blog.model.mapper.MemberMapper;
import com.example.blog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto delete(Long id) {
        Member member = this.find(id);
        memberRepository.deleteById(member.getId());
        return MemberMapper.toResponseDto(member);
    }

    // TODO: 페이징 적용
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberMapper::toResponseDto).toList();
    }

    public Member findById(Long id) {
        return this.find(id);
    }

    public MemberResponseDto findByIdAsDto(Long id) {
        Member findMember = this.find(id);
        return MemberMapper.toResponseDto(findMember);
    }

    public MemberResponseDto post(MemberPostRequestDto dto) {
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

        return MemberMapper.toResponseDto(member);
    }

    public MemberResponseDto update(Long id, MemberUpdateRequestDto memberDto) {
        Member member = this.find(id);
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        member.setPassword(encodedPassword);
        member.setIntro(memberDto.getIntro());

        return MemberMapper.toResponseDto(memberRepository.save(member));
    }

    private Member find(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }
}
