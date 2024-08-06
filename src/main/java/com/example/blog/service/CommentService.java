package com.example.blog.service;

import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.model.dto.CommentDto;
import com.example.blog.model.dto.CommentResponseDto;
import com.example.blog.model.entity.Comment;
import com.example.blog.model.mapper.CommentMapper;
import com.example.blog.repository.CommentRepository;
import com.example.blog.utils.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

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
}
