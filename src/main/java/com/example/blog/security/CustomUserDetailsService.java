package com.example.blog.security;

import com.example.blog.model.entity.Member;
import com.example.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("자격증명 인증 과정에서 사용자를 찾을 수 없습니다.");
        } else {
            userName = member.getUsername();
            password = member.getPassword();
            authorities = new ArrayList<>();
        }

        return new User(userName, password, authorities);
    }
}
