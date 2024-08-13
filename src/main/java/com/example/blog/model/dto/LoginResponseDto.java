package com.example.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String message;
    public LoginResponseDto(String message) {
        this.message = message;
    }
}
