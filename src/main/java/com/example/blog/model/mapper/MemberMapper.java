package com.example.blog.model.mapper;

import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MemberMapper {
    public static Member toEntity(MemberDto dto) {

        if (dto == null) {
            return null;
        }

        return new Member(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getIntro()
        );
    }

    public static MemberDto toDto(Member member) {

        if (member == null) {
            return null;
        }

        return new MemberDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getIntro()
        );

    }

    public static List<MemberDto> toDtos(List<Member> members) {
        if (members == null) {
            return null;
        }

        return members.stream().map(MemberMapper::toDto).toList();
    }
}
