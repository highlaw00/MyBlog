package com.example.blog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String contents;

    public Article(
            Long id, String title, String contents
    ) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Article (
            Long id,
            Member member,
            String title,
            String contents
    ) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.contents = contents;
    }
}
