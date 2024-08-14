package com.example.blog.model.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPatchRequestDto {
    @NotNull
    Long memberId;

    @NotNull
    @NotBlank
    String contents;
}
