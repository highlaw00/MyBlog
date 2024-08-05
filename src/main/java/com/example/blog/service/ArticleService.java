package com.example.blog.service;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.MemberDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.mapper.ArticleMapper;
import com.example.blog.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public List<ArticleDto> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleMapper::toDto).toList();
    }

    public ArticleDto findById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        return ArticleMapper.toDto(articleOptional.orElseThrow(ArticleNotFoundException::new));
    }

    public ArticleDto post(ArticleDto dto) {
        MemberDto memberDto = memberService.findById(dto.getMemberId());
        Article article = ArticleMapper.toEntity(dto, memberDto);
        articleRepository.save(article);
        return ArticleMapper.toDto(article);
    }
}
