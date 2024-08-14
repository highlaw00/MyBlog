package com.example.blog.model.dto.member;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPostRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "조건에 부합하지 않는 ID입니다.")
    private String username;

    // 최소 8글자 ~ 최대 30글자 & 영어, 숫자, 특수 기호 포함 가능.
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/]{8,30}$", message = "조건에 부합하지 않는 비밀번호입니다.")
    private String password;

}
