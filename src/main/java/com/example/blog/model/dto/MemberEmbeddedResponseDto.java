package com.example.blog.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEmbeddedResponseDto {
    private Long id;
    private String username;

    public MemberEmbeddedResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
