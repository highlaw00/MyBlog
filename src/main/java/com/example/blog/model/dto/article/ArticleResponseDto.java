package com.example.blog.model.dto.article;

import com.example.blog.model.dto.member.MemberResponseDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {
    private Long id;
    private MemberResponseDto member;
    private String title;
    private String contents;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.views = article.getViews();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();

        this.member = MemberMapper.toResponseDto(article.getMember());
    }

}
