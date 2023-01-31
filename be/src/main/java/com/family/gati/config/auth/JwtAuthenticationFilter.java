package com.family.gati.config.auth;

import com.family.gati.service.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
// JWT Token을 검증해주는 Filter
public class JwtAuthenticationFilter extends GenericFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); // 헤더에서 token 추출

        if (token != null && jwtTokenProvider.validateTokenExceptExpiration(token)) { // token이 존재하고 만료시간이 지나지 않았다면
            Authentication auth = jwtTokenProvider.getAuthentication(token); // 인증 객체 받아오고
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 저장하여 인증할 수 있도록
        }
        // 다음 Filter로 넘어가 Authentification Filter에서 이미 인증되어 있는 객체를 통해 인증이 되도록
        chain.doFilter(request, response);
    }
}
