package com.example.blog.model.mapper;

import com.example.blog.model.dto.article.ArticleEmbeddedResponseDto;
import com.example.blog.model.dto.article.ArticlePostRequestDto;
import com.example.blog.model.dto.article.ArticleResponseDto;
import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.entity.Member;

public class ArticleMapper {
    public static Article toEntityFromPostRequest(ArticlePostRequestDto articleDto, Member member) {
        if (articleDto == null) {
            return null;
        }
        return new Article(articleDto.getId(), member, articleDto.getTitle(), articleDto.getContents());
    }

    public static ArticleResponseDto toResponseDto(Article article) {
        if (article == null) {
            return null;
        }

        MemberResponseDto memberResponseDto = MemberMapper.toResponseDto(article.getMember());

        return new ArticleResponseDto(
                article.getId(),
                memberResponseDto,
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
