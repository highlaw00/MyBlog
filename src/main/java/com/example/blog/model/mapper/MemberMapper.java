package com.example.blog.model.mapper;

import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Member;

public class MemberMapper {
    public static Member toEntity(MemberDto dto) {
        Member member = new Member();

        member.setId(dto.getId());
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());
        member.setIntro(dto.getIntro());

        return member;
    }

    public static MemberDto toDto(Member member) {
        MemberDto dto = new MemberDto();

        dto.setId(member.getId());
        dto.setUsername(member.getUsername());
        dto.setPassword(member.getPassword());
        dto.setId(member.getId());

        return dto;
    }
}
