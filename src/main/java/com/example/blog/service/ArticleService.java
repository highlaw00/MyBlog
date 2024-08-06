package com.example.blog.service;

import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.model.dto.ArticleDto;
import com.example.blog.model.dto.ArticleResponseDto;
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

    public ArticleResponseDto findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        return ArticleMapper.toResponseDto(article);
    }

    /**
     * 수정하려는 Article Entity의 member는 변경되지 않습니다.
     */
    public ArticleResponseDto update(ArticleDto dto) {
        Article article = this.find(dto.getId());
        article.updateTitleAndContents(dto);
        articleRepository.saveAndFlush(article);
        return ArticleMapper.toResponseDto(article);
    }

    public ArticleResponseDto post(ArticleDto dto) {
        MemberDto memberDto = getMemberDto(dto);
        Article article = ArticleMapper.toEntity(dto, memberDto);
        article = articleRepository.save(article);
        return ArticleMapper.toResponseDto(article);
    }

    private MemberDto getMemberDto(ArticleDto dto) {
        return memberService.findById(dto.getMemberId());
    }

    private Article find(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }
}
