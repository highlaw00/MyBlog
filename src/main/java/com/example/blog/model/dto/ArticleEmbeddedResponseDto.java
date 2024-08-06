package com.example.blog.model.dto;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleEmbeddedResponseDto(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
