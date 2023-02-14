package com.family.gati.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
/*
    모든 Request에서 JWT를 검사하는 필터
    Http Request header에 Authorization: Bearer <JWT> 형태로 전송된 Access Token을 검사하고
    유효하다면 TokenProvider에서 생성된 Authentication 객체를 SecurityContext에 저장
    여기서 SecurityContext에 저장된 Authentication 정보는 Controller에서 @AuthenticationPrincipal로 꺼내 사용 가능
    tokenProvider.getAuthentication()에서 제공된 class 타입과 @AuthenticationPrincipal에서 사용하는 class 타입 일치해야 함
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    // 인증에서 제외할 url -> 적용 안되는듯 일단;
//    private static final List<String> EXCLUDE_URL = Collections.unmodifiableList(
//            Arrays.asList(
//                    "/**/join",
//                    "/**/login",
//                    "/**/user/id",
//                    "/api/v2/api-docs",
//                    "/api/swagger.json",
//                    "api/swagger-ui.html#",
//                    "/api/swagger-resources",
//                    "/api/webjars"
//                    ,"/api/swagger.json"
//                    ,"/api/swagger-ui.html"
//                    ,"/api/swagger-resources"
//            )
//    );
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getContextPath()));
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String token = parseBearerToken(request); // 추출한 token

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            log.debug("token의 userId", tokenProvider.getUserId(token));
            System.out.println(tokenProvider.getUserId(token));
            if(!authentication.getName().matches(tokenProvider.getUserId(token))){ // 다른 유저의 토큰일 경우 예외처리
                log.debug("다른 유저의 토큰입니다.");
                throw new ServletException();
            }

            // 시큐리티에 authentication 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug(authentication.getName() + "의 인증정보 저장");
        }else{
            log.debug("유효하지 않은 토큰입니다.");
        }

        filterChain.doFilter(request, response);
    }

    // request에서 "Authorization" token 추출
    public String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
