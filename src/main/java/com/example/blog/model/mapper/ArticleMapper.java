package com.example.blog.model.mapper;

import com.example.blog.model.dto.ArticleDto;
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
}
