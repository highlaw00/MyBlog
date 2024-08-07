package com.example.blog.repository;

import com.example.blog.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.article a where a.id=:articleId")
    List<Comment> findCommentsByArticleId(@Param("articleId") Long articleId);

    @Query("select c from Comment c join c.member m where m.id=:memberId")
    List<Comment> findCommentsByMemberId(@Param("memberId") Long memberId);
}
