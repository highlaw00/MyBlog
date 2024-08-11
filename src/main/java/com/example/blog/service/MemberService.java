package com.example.blog.service;

import com.example.blog.exception.MemberDuplicatedException;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.exception.MemberNotFoundException;
import com.example.blog.model.entity.Member;
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

    public void checkDuplication(MemberDto dto) {
        Member member = memberRepository.findByUsername(dto.getUsername());
        if (member != null) {
            throw new MemberDuplicatedException();
        }
    }

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

    public MemberDto post(MemberDto dto) {
        String rawPassword = dto.getPassword();
        dto.setPassword(passwordEncoder.encode(rawPassword));
        Member member = MemberMapper.toEntity(dto);
        member = memberRepository.save(member);
        // debate
        // - MemberMapper.toDto는 내부적으로 new를 사용해 객체를 생성한다. 오버헤드는 없을까?
        // - 이 메서드가 실행될 때, Member와 MemberDto가 다른 점은 영속성 컨텍스트로부터 받아온 id가 존재하는지 아닌지이다. setter를 사용해 id만 변경하는건 어떨까?
        dto = MemberMapper.toDto(member);

        return dto;
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
