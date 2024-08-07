package com.example.blog.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPostRequestDto {
    @NotNull
    Long memberId;

    @NotNull
    Long articleId;

    @NotNull
    @NotBlank
    String contents;
}
