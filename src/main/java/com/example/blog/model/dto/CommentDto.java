package com.example.blog.model.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private MemberDto memberDto;
    private String contents;

}
