package com.family.gati.service;

import com.family.gati.repository.UserRepository;
import com.family.gati.security.jwt.JwtTokenProvider;
import com.family.gati.security.oauth.CustomUserDetails;
import com.family.gati.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        // 1. Validation Refresh Token
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("no Refresh Token Cookie"));

        if (!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Not Validated Refresh Token");
        }

        // 2. 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        int userSeq = Integer.valueOf(user.getName());

        // 3. Match Refresh Token
        String savedToken = userRepository.getRefreshTokenByUserSeq(userSeq);

        if (!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        // 4. JWT 갱신
        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }
}
