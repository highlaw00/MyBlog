package com.example.blog.model.dto.member;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberUpdateRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/]{8,30}$", message = "조건에 부합하지 않는 비밀번호입니다.")
    private String password;
    @Size(max = 255, message = "사용자 한 줄 소개는 최대 255글자 입니다.")
    private String intro;

}
