package com.example.blog.service;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.model.dto.article.ArticlePostRequestDto;
import com.example.blog.model.dto.article.ArticleResponseDto;
import com.example.blog.model.dto.article.ArticleUpdateRequestDto;
import com.example.blog.model.entity.Article;
import com.example.blog.model.entity.Member;
import com.example.blog.model.mapper.ArticleMapper;
import com.example.blog.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public ArticleResponseDto delete(Long id) {
        Article article = this.find(id);
        articleRepository.delete(article);
        return ArticleMapper.toResponseDto(article);
    }

    public List<ArticleResponseDto> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleMapper::toResponseDto).toList();
    }

    public List<ArticleResponseDto> findAll(Long memberId) {
        List<Article> articles = articleRepository.findArticlesByMemberId(memberId);
        return articles.stream().map(ArticleMapper::toResponseDto).toList();
    }

    public Article findById(Long id) {
        return this.find(id);
    }

    public ArticleResponseDto findByIdAsResponseDto(Long id) {
        Article article = this.find(id);
        return ArticleMapper.toResponseDto(article);
    }

    /**
     * 수정하려는 Article Entity의 member는 변경되지 않습니다.
     */
    public ArticleResponseDto update(Long id, ArticleUpdateRequestDto dto) {
        Article article = this.find(id);
        article.updateTitleAndContents(dto);
        articleRepository.saveAndFlush(article);
        return ArticleMapper.toResponseDto(article);
    }

    public ArticleResponseDto post(ArticlePostRequestDto dto) {
        Member member = memberService.findById(dto.getMemberId());
        Article article = ArticleMapper.toEntityFromPostRequest(dto, member);
        article = articleRepository.save(article);
        return ArticleMapper.toResponseDto(article);
    }

    private Article find(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }
}
