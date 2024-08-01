package com.example.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    @NotNull(message = "사용자 이름이 누락되었습니다.")
    @Size(min = 2, max = 50, message = "사용자 이름은 2글자에서 50글자 사이여야 합니다.")
    private String username;

    @NotNull(message = "비밀번호가 누락되었습니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4글자에서 20글자 사이여야 합니다.")
    private String password;

    @Size(max = 255, message = "사용자 한 줄 소개는 최대 255글자 입니다.")
    private String intro;

}
