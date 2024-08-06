package com.example.blog.model.entity;

import com.example.blog.model.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
public class Article extends BaseEntity {
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

    /**
     * 작성자가 변경될 일이 없기 때문에 member는 변경하지 않음
     */
    public void updateTitleAndContents(ArticleDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
