package com.example.blog.model.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequestDto {
    private Long id;
    @NotNull
    private Long memberId;

    @NotNull(message = "제목은 없을 수 없습니다.")
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    @Size(max = 255)
    private String title;

    @NotNull(message = "본문의 내용은 없을 수 없습니다.")
    @Size(max = 20_000, message = "본문의 내용은 20000글자 이하여야 합니다.")
    private String contents;
}
