package com.example.blog.model.mapper;

import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.ArticleEmbeddedResponseDto;
import com.example.blog.model.dto.ArticleResponseDto;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.entity.Member;

public class ArticleMapper {
    public static Article toEntity(ArticleDto dto, MemberDto memberDto) {
        if (dto == null) {
            return null;
        }
        Member member = MemberMapper.toEntity(memberDto);
        return new Article(dto.getId(), member, dto.getTitle(), dto.getContents());
    }

    public static ArticleDto toDto(Article article) {
        if (article == null) {
            return null;
        }
        return new ArticleDto(
                article.getId(),
                article.getMember().getId(),
                article.getTitle(),
                article.getContents()
        );
    }

    public static ArticleResponseDto toResponseDto(Article article) {
        if (article == null) {
            return null;
        }

        return new ArticleResponseDto(
                article.getId(),
                article.getMember().getId(),
                article.getTitle(),
                article.getContents(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }

    public static ArticleEmbeddedResponseDto toEmbeddedResponseDto(Article article) {
        if (article == null) {
            return null;
        }

        return new ArticleEmbeddedResponseDto(
                article.getId(),
                article.getTitle(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }
}
