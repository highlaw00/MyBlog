package com.example.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.stream.Collectors;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String JSON_USERNAME_KEY = "username";

    private static final String JSON_PASSWORD_KEY = "password";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private boolean postOnly = true;

//    @Override
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        super.setAuthenticationManager(authenticationManager);
//    }

    public JsonAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDto jsonData = null;
        String username, password;
        try {
//            jsonData = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            jsonData = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining()), LoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (jsonData == null) {
            username = "";
            password = "";
        } else {
            username = jsonData.username;
            password = jsonData.password;
        }
        UsernamePasswordAuthenticationToken authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return null;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class LoginDto {
        private String username;
        private String password;
    }
}
