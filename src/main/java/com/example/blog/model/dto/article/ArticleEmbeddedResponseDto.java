package com.example.blog.model.dto.article;

import com.example.blog.model.entity.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleEmbeddedResponseDto {
    /**
     * 임베딩 된 본문 내용에 포함될 내용
     * ArticleID, ArticleCreatedAt, ArticleUpdatedAt, ArticleTitle
     */
    private Long id;
    private String title;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleEmbeddedResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.views = article.getViews();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
