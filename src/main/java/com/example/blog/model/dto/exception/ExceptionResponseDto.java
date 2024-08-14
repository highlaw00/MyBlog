package com.example.blog.model.dto.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponseDto {

    private HttpStatus status;
    private String message;

    public ExceptionResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
