package com.example.blog.model.mapper;

import com.example.blog.model.dto.*;
import com.example.blog.model.entity.Comment;
import com.example.blog.model.entity.Member;

public class CommentMapper {

    public static Comment toEntity(CommentPostRequestDto dto) {
        Comment comment = new Comment();

        comment.setContents(dto.getContents());

        return comment;
    }

    public static CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setContents(comment.getContents());

        MemberDto memberDto = MemberMapper.toDto(comment.getMember());
        dto.setMemberDto(memberDto);

        return dto;
    }

    public static CommentResponseDto toResponseDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();

        MemberEmbeddedResponseDto memberDto = MemberMapper.toEmbeddedResponseDto(comment.getMember());
        ArticleEmbeddedResponseDto articleDto = ArticleMapper.toEmbeddedResponseDto(comment.getArticle());
        dto.setId(comment.getId());
        dto.setMember(memberDto);
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        dto.setArticle(articleDto);
        dto.setContents(comment.getContents());

        return dto;
    }
}
