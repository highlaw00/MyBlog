package com.example.blog.model.dto.comment;

import com.example.blog.model.dto.member.MemberEmbeddedResponseDto;
import com.example.blog.model.dto.article.ArticleEmbeddedResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private ArticleEmbeddedResponseDto article;
    private MemberEmbeddedResponseDto member;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
