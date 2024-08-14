package com.example.blog.model.mapper;

import com.example.blog.model.dto.member.MemberEmbeddedResponseDto;
import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.dto.member.MemberPostRequestDto;
import com.example.blog.model.entity.Member;

public class MemberMapper {

    public static Member toEntity(MemberPostRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return new Member(dto.getUsername(), dto.getPassword());
    }

    public static MemberEmbeddedResponseDto toEmbeddedResponseDto(Member member) {

        if (member == null) {
            return null;
        }

        return new MemberEmbeddedResponseDto(member.getId(), member.getUsername());
    }

    public static MemberResponseDto toResponseDto(Member member) {
        if (member == null) {
            return null;
        }

        return new MemberResponseDto(member);
    }
}
