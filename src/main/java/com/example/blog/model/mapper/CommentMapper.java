package com.example.blog.model.mapper;

import com.example.blog.model.dto.article.ArticleEmbeddedResponseDto;
import com.example.blog.model.dto.comment.CommentPostRequestDto;
import com.example.blog.model.dto.comment.CommentResponseDto;
import com.example.blog.model.dto.member.MemberEmbeddedResponseDto;
import com.example.blog.model.entity.Comment;

public class CommentMapper {

    public static Comment toEntity(CommentPostRequestDto dto) {
        Comment comment = new Comment();

        comment.setContents(dto.getContents());

        return comment;
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
