package com.example.blog.security;

import com.example.blog.model.entity.Member;
import com.example.blog.model.enums.MemberRole;
import com.example.blog.utils.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            // 헤더 양식 불량
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            // 토큰 만료
            filterChain.doFilter(request, response);
        }

        // TODO: 엔티티 사용하지 않고 구현하기
        String username = jwtUtil.getUsername(token);
        MemberRole role = MemberRole.valueOf(jwtUtil.getRole(token));

        Member sessionMember = new Member();
        sessionMember.setUsername(username);
        sessionMember.setRole(role);

        CustomUserDetails customUserDetails = new CustomUserDetails(sessionMember);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
