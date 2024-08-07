package com.example.blog.repository;

import com.example.blog.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a inner join a.member m where m.id=:memberId")
    List<Article> findArticlesByMemberId(@Param("memberId") Long memberId);

}
