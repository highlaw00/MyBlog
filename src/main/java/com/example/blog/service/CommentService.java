package com.example.blog.service;

import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.model.dto.comment.CommentPatchRequestDto;
import com.example.blog.model.dto.comment.CommentPostRequestDto;
import com.example.blog.model.dto.comment.CommentResponseDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.entity.Comment;
import com.example.blog.model.entity.Member;
import com.example.blog.model.mapper.CommentMapper;
import com.example.blog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;
    private final MemberService memberService;

    public int delete(Long id) {
        Comment comment = this.find(id);
        commentRepository.delete(comment); // Exception 처리 고려
        return 1;
    }

    public CommentResponseDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        return CommentMapper.toResponseDto(comment);
    }

    public List<CommentResponseDto> findAllOnArticle(Long articleId) {
        List<Comment> commentsByArticleId = commentRepository.findCommentsByArticleId(articleId);
        return commentsByArticleId.stream().map(CommentMapper::toResponseDto).toList();
    }

    public List<CommentResponseDto> findAllOnMember(Long memberId) {
        List<Comment> comments = commentRepository.findCommentsByMemberId(memberId);
        return comments.stream().map(CommentMapper::toResponseDto).toList();
    }

    public CommentResponseDto post(CommentPostRequestDto dto) {
        Comment comment = CommentMapper.toEntity(dto);
        Article article = articleService.findById(dto.getArticleId());
        Member member = memberService.findById(dto.getMemberId());

        comment.setMember(member);
        comment.setArticle(article);

        commentRepository.saveAndFlush(comment);

        return CommentMapper.toResponseDto(comment);
    }

    public CommentResponseDto update(Long id, CommentPatchRequestDto dto) {
        Comment comment = this.find(id);
        comment.updateContents(dto);
        commentRepository.saveAndFlush(comment);
        return CommentMapper.toResponseDto(comment); // 이 과정에서 Member, Article에 접근해서 추가 쿼리가 발생.
    }

    private Comment find(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
